/**
 * Created by Administrator on 2017/4/12.
 */

'use strict';

angular.module('myApp')

    /* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
    .config(['$ocLazyLoadProvider', function ($ocLazyLoadProvider) {
        $ocLazyLoadProvider.config({
            // global configs go here
        });
    }])

    //AngularJS v1.3.x workaround for old style controller declarition in HTML
    .config(['$controllerProvider', function ($controllerProvider) {
        // this option might be handy for migrating old apps, but please don't use it
        // in new ones!
        $controllerProvider.allowGlobals();
    }])

 /********************************************
 END: BREAKING CHANGE in AngularJS v1.3.x:
 *********************************************/

    /**
     * 后端会读取请求中header的X-Requested-With字段判断前端的请求是否为异步请求XMLHttpRequest，在使用$http服务发送请求时后端却判断为false。
     * 'X-Requested-With' : 'XMLHttpRequest'并不属于标准的header内容，因此Angular不会在header中默认设置该字段。
     */
    .config(['$httpProvider',function ($httpProvider) {
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $httpProvider.interceptors.push('myHttpInterceptor');
    }])


    //自定义$http服务拦截器
    .factory('myHttpInterceptor',['$q','$rootScope',function ($q,$rootScope) {
        return{
            'request':function (config) {
                return config;
            },
            'requestError':function (rejection) {
                return $q.reject(rejection);
            },
            'response':function (response) {
                if('undefined' != typeof (response.data.flag) && response.data.flag != ''){
                    if(response.data.flag >0)
                        toastr.success(response.data.message);
                    else
                        toastr.danger(response.data.message);
                }

                return response;
            },
            'responseError':function (rejection) {
                if(rejection.status === 401){    //未登录或者session过期错误
                    var getTopWinow = function (){
                        var p = window;
                        while(p != p.parent){
                            p = p.parent;
                        }
                        return p;
                    };
                    if($rootScope.isFirstConfirm){
                        var yes = confirm('由于您长时间没有操作, session已过期, 请重新登录.');
                        $rootScope.isFirstConfirm = false;
                        if (yes) {
                            var top = getTopWinow();
                            top.location.href = CONSTANT.LOGINURL;
                        }
                    }
                }
                return $q.reject(rejection);
            }
        };
    }])


    /* factory function safeApply
     *
     * @description If you find yourself triggering the '$apply already in progress' error while developing with Angular.JS
     * (for me I find I hit most often when integrating third party plugins that trigger a lot of DOM events),
     * you can use a 'safeApply' method that checks the current phase before executing your function.
     *
     * @param scope, the action scope, mostly is the topmost controller
     * @param fn, the function which you want to apply into scope
     * @see  https://coderwall.com/p/ngisma
     */
    .factory('safeApply', function() {
        return function(scope, fn) {
            var phase = scope.$root.$$phase;
              if (phase == '$apply' || phase == '$digest') {
                  if (fn && ( typeof (fn) === 'function')) {
                     fn();
                   }
              } else {
                   scope.$apply(fn);
                 }
         }
     })

    .factory('settings', ['$rootScope', function($rootScope) {
     // supported languages
     var settings = {
         layout: {
             pageSidebarClosed: false, // sidebar menu state
             pageContentWhite: true, // set page content layout
             pageBodySolid: false, // solid body color state
             pageAutoScrollOnLoad: 100 // auto scroll to top on page load
         },
         assetsPath: '../statics/assets',
         globalPath: '../statics/assets/global',
         layoutPath: '../statics/assets/layouts/layout3',
     };

     $rootScope.settings = settings;

     return settings;
 }]);




