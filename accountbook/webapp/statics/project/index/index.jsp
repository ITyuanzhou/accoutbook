<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js" data-ng-app="myApp"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js" data-ng-app="myApp"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" data-ng-app="myApp">
    <!--<![endif]-->

    <!-- BEGIN HEAD -->
    <head>
        <title>记账本</title>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="记账本网页" name="description" />
        <meta content="zhouyuan" name="author" />

        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <%--<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />--%>
        <link href="../statics/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="../statics/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="../statics/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="../statics/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <link href="../statics/assets/global/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
        <link href="../statics/assets/global/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN DYMANICLY LOADED CSS FILES(all plugin and page related styles must be loaded between GLOBAL and THEME css files ) -->
        <link id="ng_load_plugins_before" />
        <!-- END DYMANICLY LOADED CSS FILES -->
        <!-- BEGIN THEME STYLES -->
        <!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
        <link href="../statics/assets/global/css/components.min.css" id="style_components" rel="stylesheet" type="text/css" />
        <link href="../statics/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <link href="../statics/assets/layouts/layout3/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="../statics/assets/layouts/layout3/css/themes/default.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="../statics/assets/layouts/layout3/css/custom.css" rel="stylesheet" type="text/css" />
        <!-- END THEME STYLES -->
        <link rel="shortcut icon" href="favicon.ico" />
    </head>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->
    <!-- DOC: Apply "page-header-menu-fixed" class to set the mega menu fixed  -->
    <!-- DOC: Apply "page-header-top-fixed" class to set the top menu fixed  -->

    <body ng-controller="AppController">
       <!-- BEGIN PAGE SPINNER -->
       <div ng-spinner-bar class="page-spinner-bar">
        <div class="bounce1"></div>
        <div class="bounce2"></div>
        <div class="bounce3"></div>
    </div>
       <!-- END PAGE SPINNER -->

       <div class="page-wrapper">
            <div class="page-wrapper-row">
                <div class="page-wrapper-top">
                    <!-- BEGIN HEADER -->
                    <div data-ng-include="'../statics/project/index/tpl/header.html'" data-ng-controller="HeaderController as vm" class="page-header"> </div>
                    <!-- END HEADER -->
                </div>
            </div>
            <div class="page-wrapper-row full-height">
                <div class="page-wrapper-middle">
                    <!-- BEGIN CONTAINER -->
                    <div class="page-container">
                        <!-- BEGIN PAGE HEAD -->
                        <div data-ng-include="'../statics/project/index/tpl/page-head.html'" data-ng-controller="PageHeadController" class="page-head"> </div>
                        <!-- END PAGE HEAD -->
                        <!-- BEGIN PAGE CONTENT -->
                        <div class="page-content">
                            <div class="container">
                                <!-- BEGIN ACTUAL CONTENT -->
                                <div ui-view class="fade-in-up"> </div>
                                <!-- END ACTUAL CONTENT -->
                            </div>
                        </div>
                        <!-- END PAGE CONTENT -->
                        <!-- BEGIN QUICK SIDEBAR -->
                        <a href="javascript:;" class="page-quick-sidebar-toggler">
                            <i class="icon-login"></i>
                        </a>
                        <div data-ng-include="'../statics/project/index/tpl/quick-sidebar.html'" data-ng-controller="QuickSidebarController" class="page-quick-sidebar-wrapper"></div>
                        <!-- END QUICK SIDEBAR -->
                    </div>
                    <!-- END CONTAINER -->
                </div>
            </div>
            <div class="page-wrapper-row">
                <div class="page-wrapper-bottom">
                    <!-- BEGIN FOOTER -->
                    <div data-ng-include="'../statics/project/index/tpl/footer.html'" data-ng-controller="FooterController"> </div>
                    <!-- END FOOTER -->
                </div>
            </div>
        </div>


        <!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
        <!-- BEGIN CORE JQUERY PLUGINS -->
        <!--[if lt IE 9]>
	<script src="../statics/assets/global/plugins/respond.min.js"></script>
	<script src="../statics/assets/global/plugins/excanvas.min.js"></script>
	<![endif]-->
        <script src="../statics/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/jquery-counterup/jquery.counterup.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/jquery-counterup/jquery.waypoints.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/bootstrap-confirmation/bootstrap-confirmation.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/bootstrap-select/js/bootstrap-select.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/bootstrap-select/js/i18n/defaults-zh_CN.js" type="text/javascript"></script>
        <!-- END CORE JQUERY PLUGINS -->
        <!-- BEGIN CORE ANGULARJS PLUGINS -->
        <script src="../statics/assets/global/plugins/angularjs/angular.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/angularjs/angular-sanitize.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/angularjs/angular-touch.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/angularjs/plugins/angular-ui-router.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/angularjs/plugins/ocLazyLoad.min.js" type="text/javascript"></script>
        <script src="../statics/assets/global/plugins/angularjs/plugins/ui-bootstrap-tpls.min.js" type="text/javascript"></script>
        <!-- END CORE ANGULARJS PLUGINS -->
        <!-- BEGIN APP LEVEL ANGULARJS SCRIPTS -->
        <script src="../statics/project/index/myApp.js" type="text/javascript"></script>
        <script src="../statics/project/configs/router.js" type="text/javascript"></script>
        <script src="../statics/project/configs/configs.js" type="text/javascript"></script>
        <script src="../statics/project/index/tpl/tpl.js" type="text/javascript"></script>
        <script src="../statics/project/utils/constant.js" type="text/javascript"></script>
        <script src="../statics/project/utils/date.js" type="text/javascript"></script>
        <script src="../statics/project/common/directives/directives.js" type="text/javascript"></script>
        <script src="../statics/project/common/filter/filter.js" type="text/javascript"></script>
        <script src="../statics/project/common/services/pubSubService.js" type="text/javascript"></script>
        <script src="../statics/project/common/services/businessService.js" type="text/javascript"></script>
        <!-- END APP LEVEL ANGULARJS SCRIPTS -->
        <!-- BEGIN APP LEVEL JQUERY SCRIPTS -->
        <script src="../statics/assets/global/scripts/app.min.js" type="text/javascript"></script>
        <script src="../statics/assets/layouts/layout3/scripts/layout.min.js" type="text/javascript"></script>
        <script src="../statics/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <script src="../statics/assets/layouts/layout3/scripts/demo.min.js" type="text/javascript"></script>
        <!-- END APP LEVEL JQUERY SCRIPTS -->
        <!-- END JAVASCRIPTS -->
    </body>
    <!-- END BODY -->

</html>