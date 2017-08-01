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
     'myApp.common.filter'
 ]);


myApp.run(['$rootScope','$state','settings',function($rootScope,$state,settings) {

    $rootScope.$state = $state; // state to be accessed from view
    $rootScope.$settings = settings; // state to be accessed from view

    $rootScope.serverUrl = '192.168.24.56';
}]);





