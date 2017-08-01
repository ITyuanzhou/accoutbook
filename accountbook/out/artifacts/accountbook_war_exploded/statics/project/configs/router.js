/**
 * Created by Administrator on 2017/3/24.
 */

'use strict';

angular.module('myApp')
    .config(['$locationProvider', '$urlRouterProvider', '$stateProvider', function ($locationProvider, $urlRouterProvider, $stateProvider) {

        $locationProvider.hashPrefix('!');
        //默认路由
        $urlRouterProvider.otherwise('/');

        $stateProvider
            .state('home', {
            url: '/',
            templateUrl: 'statics/project/business/home/home.jsp',
            data: {pageTitle: '主页'},
            controller: 'HomeIndexCtrl as vm',
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'myApp',
                        files: [
                            '../statics/project/business/home/home.js'
                        ]
                    });
                  }]
               }
            })

            .state('billingDetail', {
                url: '/billingDetail',
                templateUrl: 'statics/project/business/billing/billingDetail.jsp',
                data: {pageTitle: '账单明细'},
                controller: 'BillingDetailCtrl as vm',
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name: 'myApp',
                            files: [
                                '../statics/project/business/billing/billingDetail.js'
                            ]
                        });
                    }]
                }
            })

            .state('dataManage', {
                url: '/dataManage',
                templateUrl: 'statics/project/business/data/dataManage.jsp',
                data: {pageTitle: '数据管理',pageSubTitle:'管理员管理系统数据'},
                controller: 'DataManageCtrl as vm',
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name: 'myApp',
                            files: [
                                '../statics/project/business/data/dataManage.js'
                            ]
                        });
                    }]
                }
            })


    }]);
