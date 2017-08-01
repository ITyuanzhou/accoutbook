/**
 * Created by ZJZL_HP on 2017/7/19.
 */
'use strict';

angular.module('myApp.business.home',[])

    .controller('HomeIndexCtrl',['$scope','$rootScope',function($scope,$rootScope) {

        var vm = this;

        vm.billingDetailList = [
            {
                originator: '周继葵',
                btotalAmount: '340',
                btypeName: '吃饭',
                bremark: '晚饭',
                personNumber: '4',
                chargeAmount: '48',
                chargeTime:'16:52'
            },{
                originator: '周继葵',
                btotalAmount: '345',
                btypeName: '吃饭',
                bremark: '晚饭',
                personNumber: '4',
                chargeAmount: '48',
                chargeTime:'16:52'
            },{
                originator: '周继葵',
                btotalAmount: '345',
                btypeName: '吃饭',
                bremark: '晚饭',
                personNumber: '4',
                chargeAmount: '48',
                chargeTime:'16:52'
            }
        ];
        return vm;
    }])



