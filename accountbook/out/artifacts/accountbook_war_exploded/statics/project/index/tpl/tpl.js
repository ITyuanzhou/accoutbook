/**
 * Created by Administrator on 2017/4/12.
 */

'use strict';

angular.module('myApp.tpl', ['myApp.common.businessServices'])
     /* Setup App Main Controller */
    .controller('AppController', ['$scope', function ($scope) {
        $scope.$on('$viewContentLoaded', function () {
            App.initComponents(); // init core components
            //Layout.init(); //  Init entire layout(header, footer, sidebar, etc) on page load if the partials included in server side instead of loading with ng-include directive
        });
    }])

    .controller('HeaderController', ['$rootScope', '$scope','userService',
        function ($rootScope, $scope,userService) {
            var vm = this;

            $scope.$on('$includeContentLoaded', function () {
                Layout.initHeader(); // init header
            });

            var dropdownMenuInit = function () {
                vm.LockScreen = function () {
                    userService.logout();
                    window.location.href = "lock";
                };

                vm.LogOut = function () {
                    userService.logout();
                    window.location.href = "login";
                };
            };

            vm.Init = function () {
                dropdownMenuInit();
            };
            vm.Init();
        }])

    .controller('PageHeadController', ['$scope', function($scope) {
        $scope.$on('$includeContentLoaded', function() {
            Demo.init(); // init theme panel
        });
    }])

    .controller('QuickSidebarController', ['$scope', function($scope) {
      $scope.$on('$includeContentLoaded', function() {
          setTimeout(function(){
              QuickSidebar.init(); // init quick sidebar
          }, 2000)
       });
    }])

    .controller('FooterController', ['$scope', function ($scope) {
        $scope.$on('$includeContentLoaded', function () {
            Layout.initFooter(); // init footer
        });
    }])
