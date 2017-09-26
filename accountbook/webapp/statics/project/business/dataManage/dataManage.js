/**
 * Created by ZJZL_HP on 2017/7/19.
 */
'use strict';

angular.module('myApp.business.dataManage',[])

    .controller('DataManageCtrl',['$scope','$rootScope','$timeout','userService','aaBilTypeService',function($scope,$rootScope,$timeout,userService,aaBilTypeService) {
        var vm = this;

        var userManage = function () {
            var load = function () {
                userService.getAllUsers().then(function (response) {
                    vm.usersList = response;
                });

                vm.addUser = function () {
                   vm.userItem = {userPersonBalance:0,userAaBalance:0};
                   $('#userModal').modal('show');
                };
                vm.saveUser = function (item) {
                    vm.userModalCloseFlag = true;
                    userService.saveUser(item).then(function (res) {
                        if(res.flag>0){
                            if(res.map == null){
                                //vm.usersList.splice(vm.usersList.indexOf(item),1,item);
                                angular.forEach(vm.usersList,function (data,index,array) {
                                    if(data.userId == item.userId)
                                        vm.usersList[index] = item;
                                });
                            }
                            else{
                                item.userId = res.map.userId;
                                vm.usersList.unshift(item);
                            }
                            $('#userModal').modal('hide');
                        }
                        else{
                            alert("保存"+ item.userName +"用户失败!");
                        }
                    });
                };
                vm.editUser = function (item) {
                    vm.userModalCloseFlag = false;
                    vm.userItem = $.extend({},item);
                    $('#userModal').modal('show');
                };
                vm.deleteUser = function (item) {
                    if(confirm("确定要删除下面这位用户吗?\n"+item.userName) == false)
                        return;
                    userService.deleteUser(item).then(function (response) {
                        if(response.flag>0){
                            vm.usersList.splice(vm.usersList.indexOf(item),1);
                        }
                        else{
                            alert("删除"+ item.userName +"用户失败!");
                        }
                    });
                };
                vm.closeUserModal = function () {
                    $('#userModal').modal('hide');
                    vm.userModalCloseFlag = true;
                };
            };
            return{
                init:function () {
                    load();
                }
            };
        }();

        var aaBilTypeManage = function () {
            var load = function () {
                aaBilTypeService.getAllAABilType().then(function (response) {
                    vm.aaBilTypeList = response;
                });

                vm.addBillingType = function () {
                    vm.aaBilTypeItem = {aaBilTypeIsValid:1};
                    $('#aaBilTypeModal').modal('show');
                };
                vm.saveBillingType = function (item) {
                    vm.aaBilTypeModalCloseFlag = true;
                    aaBilTypeService.saveAABilType(item).then(function (res) {
                        if(res.flag>0){
                            if(res.map == null){
                                angular.forEach(vm.aaBilTypeList,function (data,index,array) {
                                    if(data.aaBilTypeId == item.aaBilTypeId)
                                        vm.aaBilTypeList[index] = item;
                                });
                            }
                            else{
                                item.aaBilTypeId = res.map.aaBilTypeId;
                                item.aaBilTypeCreUser = res.map.aaBilTypeCreUser;
                                item.aaBilTypeCreTime = res.map.aaBilTypeCreTime;
                                vm.aaBilTypeList.unshift(item);
                            }
                            $('#aaBilTypeModal').modal('hide');
                        }
                        else{
                            alert("保存"+ item.aaBilTypeName +"类别失败!");
                        }
                    });
                };
                vm.editAABilType = function (item) {
                    vm.aaBilTypeModalCloseFlag = false;
                    vm.aaBilTypeItem = $.extend({},item);
                    $('#aaBilTypeModal').modal('show');
                };
                vm.deleteAABilType = function (item) {
                    if(confirm("确定要删除下面这种账单类别吗?\n"+item.aaBilTypeName) == false)
                        return;
                    aaBilTypeService.deleteAABilType(item).then(function (response) {
                        if(response.flag>0){
                            vm.aaBilTypeList.splice(vm.aaBilTypeList.indexOf(item),1);
                        }
                    });
                };
                vm.closeBilTypeModal = function () {
                    $('#aaBilTypeModal').modal('hide');
                    vm.aaBilTypeModalCloseFlag = true;
                };
            };
            return{
                init:function () {
                    load();
                }
            };
        }();

        vm.Init = function () {
            userManage.init();
            aaBilTypeManage.init();
        };
        vm.Init();
        return vm;
    }]);
