/**
 * Created by Administrator on 2017/4/12.
 */

'use strict';

angular.module('myApp.tpl', [])
     /* Setup App Main Controller */
    .controller('AppController', ['$scope', function ($scope) {
        $scope.$on('$viewContentLoaded', function () {
            App.initComponents(); // init core components
            //Layout.init(); //  Init entire layout(header, footer, sidebar, etc) on page load if the partials included in server side instead of loading with ng-include directive
        });
    }])

    .controller('HeaderController', ['$rootScope', '$scope',
        function ($rootScope, $scope) {
            $scope.$on('$includeContentLoaded', function () {
                Layout.initHeader(); // init header
            });
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
