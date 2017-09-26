/**
 * Created by ZJZL_HP on 2017/7/19.
 */
'use strict';

angular.module('myApp.business.home',[])

    .controller('HomeIndexCtrl',['$scope','$rootScope','$timeout','detailAaBillingService','aaBilTypeService','userService',
        function($scope,$rootScope,$timeout,detailAaBillingService,aaBilTypeService,userService) {
        var vm = this;

        var domManager = function () {
            var tabPaneInit = function() {
                $('[name = "dateLabels"]').on('click',function (e) {
                    var id = $(this).attr("id");
                    var subId = id.substring(0,id.indexOf('_'));
                    var targetId = subId + '_pane';
                    var target = $('#'+targetId);        //需要显示的面板元素
                    var parent = target.parent();    //需要显示元素的父元素

                    parent.children().addClass("display-none");
                    target.removeClass("display-none");
                });
            };
            return{
              init:function () {
                  tabPaneInit();
              }
            };
        }();

        var dataManager = function () {
            var load = function () {
                //加载用户今日的交易信息
                detailAaBillingService.getDetailAaBillingList($rootScope.user.userId,todayDate()).then(function (response) {
                    vm.todayDetailAaBillingList = response;
                });


            };

            var eventInit = function () {
                vm.loadThreeDaysBillings = function (event) {
                    detailAaBillingService.getDetailAaBillingList($rootScope.user.userId,threeDaysAgoDate(new Date())).then(function (response) {
                        vm.todayDetailAaBillingList = response;
                    });
                    $(event.target).addClass("display-none");
                };

                vm.togglePay = function (item) {
                    detailAaBillingService.togglePay(item.detailAaBilId,$rootScope.user.userId).then(function (response) {
                        if(response.flag >0){
                            if(item.detailAaBilChargeState == 0){
                                item.detailAaBilChargeState = 1;
                                $rootScope.user.userAaBalance = $rootScope.user.userAaBalance - item.detailAaBilAmount;
                            }else{
                                item.detailAaBilChargeState = 0;
                                $rootScope.user.userAaBalance = $rootScope.user.userAaBalance + item.detailAaBilAmount;
                            }
                        }
                    });
                };

                vm.newBilling = function () {
                    var initData = function () {
                        //初始化模态框数据
                        if(typeof (vm.newAaBillingItem) == "undefined")
                            vm.newAaBillingItem = {};
                        else{
                            vm.newAaBillingItem.aaBilTotalAmount = undefined;
                            vm.newAaBillingItem.aaBilRemark = undefined;
                        }

                        //加载所有的账单类型
                        if(typeof (vm.allAABilType) == "undefined"){
                            aaBilTypeService.getAllAABilType().then(function (response) {
                                vm.allAABilType = response;
                                if(vm.allAABilType.length>0)
                                    vm.newAaBillingItem.aaBilBillingType = vm.allAABilType[0];
                            });
                        }
                        //加载所有的人员信息
                        if(typeof (vm.allUsers) == "undefined"){
                            userService.getAllUsers().then(function (response) {
                                vm.allUsers = response;
                                vm.newAaBillingItem.aaBilBearUserSet = [];
                            });
                        }
                    };
                    initData();

                    vm.saveNewAaBilling = function (item) {
                        var errors = $('.has-error',$('#newAaBillingModal'));
                        if(errors.length && errors.length>0){
                            toastr.warning("请将数据填写完整");
                        }else {
                            console.log(item);
                            //处理一下数据
                            item.aaBilUser = $rootScope.user;
                            item.aaBilPersonNumber = item.aaBilBearUserSet.length;
                            item.aaBilTime = new Date();
                            detailAaBillingService.saveNewAaBilling(item).then(function (response) {
                                if(response.flag >0){
                                    load();
                                    $('#newAaBillingModal').modal('hide');
                                }
                            });
                        }
                    };

                    $('#newAaBillingModal').modal('show');
                };
            };

            return{
                init:function () {
                    load();
                    eventInit();
                }
            };
        }();

        vm.Init = function () {
            domManager.init();
            dataManager.init();
        };
        vm.Init();

        return vm;
    }]);



