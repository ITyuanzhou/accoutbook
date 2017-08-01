/**
 * Created by ZJZL_HP on 2017/7/19.
 */
'use strict';

angular.module('myApp.business.data',[])

    .controller('DataManageCtrl',['$scope','$rootScope','userService',function($scope,$rootScope,userService) {
        var vm = this;


        var userManage = function () {
            var load = function () {
                userService.getAllUsers().then(function (response) {
                    vm.usersList = response;
                });

                $scope.fileNameChanged = function (fileObj) {
                    var allowExtension = ".jpg,.bmp,.gif,.png";
                    var fileExtension = fileObj.value.substring(fileObj.value.lastIndexOf(".")+1).toLowerCase();
                    var browserVersion = window.navigator.userAgent.toUpperCase();
                    if(allowExtension.indexOf(fileExtension)>-1){
                        if (fileObj.files) {//HTML5实现预览，兼容chrome、火狐7+等
                            if (window.FileReader) {
                                var reader = new FileReader();
                                reader.onload = function (e) {
                                    $('#a_avatar').css("background-image","url("+e.target.result+")");
                                }
                                reader.readAsDataURL(fileObj.files[0]);
                            }
                            else if (browserVersion.indexOf("SAFARI") > -1) {
                                alert("不支持Safari6.0以下浏览器的图片预览!");
                            }
                        }
                        else {
                            alert("不支持H5以下浏览器的图片预览!");
                        }
                    }
                    else{
                        alert("仅支持" + allowExtention + "为后缀名的文件!");
                        fileObj.value = ""; //清空选中文件
                        if (browserVersion.indexOf("MSIE") > -1) {
                            fileObj.select();
                            document.selection.clear();
                        }
                        fileObj.outerHTML = fileObj.outerHTML;
                    }

                    var formData = new FormData();
                    formData.append('file', $('#avatarFile')[0].files[0]);
                    formData.append('position', "files/avatar");
                    $.ajax({
                            url: '/upload',
                            type: 'POST',
                            cache: false,
                            data: formData,
                            processData: false,
                            contentType: false
                        }).success(function(res) {
                        console.log(res);
                        vm.userItem.uattach = res;
                    }).error(function(res) {
                        alert("上传头像文件失败！");
                    });
                    return fileObj.value;
                };

                vm.addUser = function () {
                   $('#a_avatar').css("background-image","url(statics/img/defaultAvatar.jpg)");
                   vm.userItem = {upersonBalance:0,uaaBalance:0};
                   $('#newUserModal').modal('show');
                };
                vm.saveUser = function (item) {
                    userService.saveUser(item).then(function (res) {
                        if(res.flag>0){
                            if(vm.usersList.indexOf(item)>-1)
                               vm.usersList.splice(vm.usersList.indexOf(item),1,item);
                            else{
                                item.uid = res.map.uid;
                                vm.usersList.unshift(item);
                            }
                            $('#newUserModal').modal('hide');
                        }
                        else{
                            alert("保存"+ item.uname +"用户失败!");
                        }
                    });
                };
                vm.editUser = function (item) {
                    if(item.uattach!=null)
                       $('#a_avatar').css("background-image","url("+item.uattach.afilePosition+"/"+item.uattach.afileUuid+item.uattach.afileName+")");
                    else
                        $('#a_avatar').css("background-image","url(statics/img/defaultAvatar.jpg)");
                    vm.userItem = item;
                    $('#newUserModal').modal('show');
                };
                vm.deleteUser = function (item) {
                    if(confirm("确定要删除下面这位用户吗?\n"+item.uname) == false)
                        return;
                    userService.deleteUser(item).then(function (response) {
                        if(response.flag>0){
                            vm.usersList.splice(vm.usersList.indexOf(item),1);
                        }
                        else{
                            alert("删除"+ item.uname +"用户失败!");
                        }
                    });
                };
            };

            return{
                init:function () {
                    load();
                }
            };
        }();

        vm.Init = function () {
            userManage.init()
        };
        vm.Init();
        return vm;
    }])

    .factory('userService',['$rootScope','$http','$q',
        function ($rootScope,$http,$q) {
            var serverUrl = $rootScope.serverUrl ;

            var getAllUsers = function () {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'GET',
                    url: '/user/getAllUsers',
                    params:{}
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };

            var saveUser = function (item) {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'POST',
                    url: '/user/saveUser',
                    data:item
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };

            var deleteUser = function (item) {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'GET',
                    url:'/user/deleteUser',
                    params:{
                        uid:item.uid
                    }
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };

            return {
                getAllUsers: function () {
                    return getAllUsers();
                },
                saveUser: function (item) {
                    return saveUser(item);
                },
                deleteUser: function (item) {
                    return deleteUser(item);
                }
            };

        }])
