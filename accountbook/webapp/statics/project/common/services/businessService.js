/**
 * Created by ZJZL_HP on 2017/9/10.
 */

'use strict';

angular.module('myApp.common.businessServices',[])
    .constant('commonServerUrl',CONSTANT.SERVERURL + '/accountbook')

    .factory('userService',['$rootScope','$http','$q','commonServerUrl',
        function ($rootScope,$http,$q,commonServerUrl) {
            var serverUrl = commonServerUrl;

            var getUser = function () {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'GET',
                    url: serverUrl+'/user/getUser',
                    params:{}
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };

            var getUserByUserId = function (userId) {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'GET',
                    url: serverUrl+'/user/getUserByUserId',
                    params:{
                        userId:userId
                    }
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };

            var getAllUsers = function () {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'GET',
                    url: serverUrl+'/user/getAllUsers',
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
                    url: serverUrl+'/user/saveUser',
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
                    url:serverUrl+'/user/deleteUser',
                    params:{
                        uid:item.userId
                    }
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };

            var logout = function () {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'GET',
                    url:serverUrl+'/user/logout',
                    params:{}
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };

            var logoutByUserId = function (userId) {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'GET',
                    url:serverUrl+'/user/logoutByUserId',
                    params:{
                        userId:userId
                    }
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };

            var getOnlineUsers = function () {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'GET',
                    url:serverUrl+'/user/getOnlineUsers',
                    params:{}
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };

            return {
                getUser: function () {
                    return getUser();
                },
                getUserByUserId: function (userId) {
                    return getUserByUserId(userId);
                },
                getAllUsers: function () {
                    return getAllUsers();
                },
                saveUser: function (item) {
                    return saveUser(item);
                },
                deleteUser: function (item) {
                    return deleteUser(item);
                },
                logout: function () {
                    return logout();
                },
                logoutByUserId: function (userId) {
                    return logoutByUserId(userId);
                },
                getOnlineUsers: function () {
                    return getOnlineUsers();
                }
            };

        }])

    .factory('aaBilTypeService',['$rootScope','$http','$q','commonServerUrl',
        function ($rootScope,$http,$q,commonServerUrl) {
            var serverUrl = commonServerUrl;

            var getAllAABilType = function () {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'GET',
                    url: serverUrl+'/aaBilType/getAllAABilType',
                    params:{}
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };

            var saveAABilType = function (item) {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'POST',
                    url: serverUrl+'/aaBilType/saveAABilType',
                    data:item
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };

            var deleteAABilType = function (item) {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'GET',
                    url:serverUrl+'/aaBilType/deleteAABilType',
                    params:{
                        aaBilTypeId:item.aaBilTypeId
                    }
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };

            return {
                getAllAABilType: function () {
                    return getAllAABilType();
                },
                saveAABilType: function (item) {
                    return saveAABilType(item);
                },
                deleteAABilType: function (item) {
                    return deleteAABilType(item);
                }
            };

        }])

    .factory('detailAaBillingService',['$rootScope','$http','$q','commonServerUrl',
        function ($rootScope,$http,$q,commonServerUrl) {
            var serverUrl = commonServerUrl;

            var getDetailAaBillingList = function (userId,time) {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'GET',
                    url: serverUrl+'/detailAaBilling/getDetailAaBillingList',
                    params:{
                        userId:userId,
                        time:time
                    }
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };

            var togglePay = function (detailAABilId,userId) {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'GET',
                    url: serverUrl+'/detailAaBilling/togglePay',
                    params: {
                        detailAABilId:detailAABilId,
                        userId:userId
                    }
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };

            var saveNewAaBilling = function (item) {
                var defer = $q.defer(); //创建延迟对象
                $http({
                    method:'POST',
                    url: serverUrl+'/detailAaBilling/saveNewAaBilling',
                    data: item
                }).success(function(data, status, headers, cfg) {
                    defer.resolve(data);
                }).error(function(data, status, headers, cfg) {
                    defer.reject(data);
                });
                return defer.promise;
            };


            return {
                getDetailAaBillingList: function (userId,time) {
                    return getDetailAaBillingList(userId,time);
                },
                togglePay: function (detailAABilId,userId) {
                    return togglePay(detailAABilId,userId);
                },
                saveNewAaBilling: function (item) {
                    return saveNewAaBilling(item);
                }
            };

        }])