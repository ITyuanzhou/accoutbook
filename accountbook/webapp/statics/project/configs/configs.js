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


