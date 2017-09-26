/**
 * Created by Administrator on 2017/3/7.
 */
'use strict';

 var myApp = angular.module('myApp', [
     'ui.router',
     "ui.bootstrap",
     'oc.lazyLoad',
     'ngSanitize',

     'myApp.tpl',
     'myApp.common.directives',
     'myApp.common.services',
     'myApp.common.businessServices',
     'myApp.common.filter'
 ]);


myApp.run(['$rootScope','$state','settings','userService',function($rootScope,$state,settings,userService) {

    $rootScope.$state = $state; // state to be accessed from view
    $rootScope.$settings = settings; // state to be accessed from view


    var run = function(){
        //全局变量初始化
        var globalVaribaleInit = function () {
            $rootScope.isFirstConfirm = true;                  //第一次弹未登录框变量
        };

        //全局ajax设置
        var ajaxInit = function () {
            /**
             * 设置未来(全局)的AJAX请求默认选项
             * 主要设置了AJAX请求遇到Session过期的情况
             */
            $.ajaxSetup({
                complete: function(xhr) {
                    if(xhr.status === 401){
                        if($rootScope.isFirstConfirm){
                            var top = getTopWinow();
                            var yes = confirm('由于您长时间没有操作, session已过期, 请重新登录.');
                            $rootScope.isFirstConfirm = false;
                            if (yes) {
                                top.location.href = CONSTANT.LOGINURL;
                            }
                        }
                    }
                }
            });

            /**
             * 在页面中任何嵌套层次的窗口中获取顶层窗口
             * @return 当前页面的顶层窗口对象
             */
            function getTopWinow(){
                var p = window;
                while(p != p.parent){
                    p = p.parent;
                }
                return p;
            }
        };

        //三方插件配置
        var pluins = function () {
            var toastr = function () {
                toastr.options = {
                    closeButton: true,
                    debug: false,
                    progressBar: true,
                    positionClass: "toast-top-right",
                    onclick: null,
                    showDuration: "300",
                    hideDuration: "1000",
                    timeOut: "3000",
                    extendedTimeOut: "1000",
                    showEasing: "swing",
                    hideEasing: "linear",
                    showMethod: "fadeIn",
                    hideMethod: "fadeOut"
                };
            };

           return{
               init:function () {
                   toastr();
               }
           };
        }();

        //全局用户信息初始化
        var userInit = function () {
            userService.getUser().then(function (response) {
                $rootScope.user = response;
            });
        };

        return{
          init:  function () {
              globalVaribaleInit();
              ajaxInit();
              pluins.init();
              userInit();
          }
        };
    }();

    run.init();
}]);





