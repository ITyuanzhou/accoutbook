/**
 * Created by ZJZL_HP on 2017/7/19.
 */
'use strict';

angular.module('myApp.business.dataStatistics',[])

    .controller('DataStatisticsCtrl',['$scope','$rootScope','$timeout','userService',function($scope,$rootScope,$timeout,userService) {
        var vm = this;

        var onlineUsers = function () {
            var loadData = function () {
                userService.getOnlineUsers().then(function (response) {
                    vm.onlineUsersList = response;
                });
            };

            var eventManager = function () {

                var logoutConfirm = function(){
                    vm.logoutConfirm = function (item) {
                        userService.logoutByUserId(item.userId).then(function (response) {
                            if(response.flag >0) {
                                vm.onlineUsersList.splice(vm.onlineUsersList.indexOf(item),1);
                            }
                        });
                    };
                };

                return{
                    init:function () {
                        logoutConfirm();
                    }
                };
            }();

            return{
                init:function () {
                    loadData();
                    eventManager.init();
                }
            };
        }();

        vm.Init = function () {
            onlineUsers.init();
        };
        vm.Init();
        return vm;
    }]);
