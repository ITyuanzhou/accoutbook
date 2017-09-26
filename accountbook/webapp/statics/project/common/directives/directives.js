/***
GLobal Directives
***/

// Route State Load Spinner(used on page or content load)
angular.module('myApp.common.directives',[])

    /**************************************UI框架指令******************************************/
    .directive('ngSpinnerBar', ['$rootScope', '$state',
    function($rootScope, $state) {
        return {
            link: function(scope, element, attrs) {
                // by defult hide the spinner bar
                element.addClass('hide'); // hide spinner bar by default

                // display the spinner bar whenever the route changes(the content part started loading)
                $rootScope.$on('$stateChangeStart', function () {
                    element.removeClass('hide'); // show spinner bar
                    Layout.closeMainMenu();
                });

                // hide the spinner bar on rounte change success(after the content loaded)
                $rootScope.$on('$stateChangeSuccess', function () {
                    element.addClass('hide'); // hide spinner bar
                    $('body').removeClass('page-on-load'); // remove page loading indicator
                    Layout.setAngularJsMainMenuActiveLink('match', null, $state); // activate selected link in the sidebar menu

                    // auto scorll to page top
                    setTimeout(function () {
                        App.scrollTop(); // scroll to the top on content load
                    }, $rootScope.settings.layout.pageAutoScrollOnLoad);
                });

                // handle errors
                $rootScope.$on('$stateNotFound', function () {
                    element.addClass('hide'); // hide spinner bar
                });

                // handle errors
                $rootScope.$on('$stateChangeError', function () {
                    element.addClass('hide'); // hide spinner bar
                });
            }
        };
    }
])

    // Handle global LINK click
    .directive('a', function() {
    return {
        restrict: 'E',
        link: function(scope, elem, attrs) {
            if (attrs.ngClick || attrs.href === '' || attrs.href === '#') {
                elem.on('click', function(e) {
                    e.preventDefault(); // prevent link click for above criteria
                });
            }
        }
    };
})

    //Handle Dropdown Hover Plugin Integration
    .directive('dropdownMenuHover', function () {
  return {
    link: function (scope, elem) {
      elem.dropdownHover();
    }
  };
})
    /**************************************UI框架指令******************************************/






    /**************************************第三方插件指令Start******************************************/




    /*******************组件型指令****************/
    /*
     * angular directive ng-Switch
     *
     * @description bootstrap-switch is a plugin of jquery and bootstrap for show switch, now I complied it with angular directive
     * @require jquery, bootstrap ,bootstrap-switch
     * @example  <ng-switch options="{onText:'有效',offText:'无效',onColor:'success',offColor:'default'}" ng-model="vm.aaBilTypeItem.aaBilTypeIsValid"></ng-switch>
     */
    .directive('ngSwitch',function () {
        return{
            restrict:'E',
            require:'ngModel',
            template: function () {
                return '<input type="checkbox" checked>';
            },
            link:function (scope,elem,attrs,ctrl) {
                var input = elem.find('input');
                var options = scope.$eval(attrs.options);
                var onSwitchChange = {
                    onSwitchChange:function(event,state) {
                        if (state)
                            ctrl.$setViewValue(1);
                        else
                            ctrl.$setViewValue(0);
                    }
                };
                $(input).bootstrapSwitch($.extend(options,onSwitchChange));

                ctrl.$render = function () {
                    var state = false;
                    if(ctrl.$viewValue == 1)
                        state = true;
                    $(input).bootstrapSwitch('state',state);
                };
            }
        }
    })
    /*******************组件型指令****************/





    /*******************装饰器型指令Start****************/
    /*
     * angular directive ng-confirmation
     *
     * @description bootstrap-confirmation is a plugin of jquery and bootstrap for show confirmation, now I complied it with angular directive
     * @require jquery, bootstrap ,bootstrap-confirmation
     * @example  <a title="确定强制退出该用户？" groupname="logoutConfirm" on-confirm="vm.logoutConfirm(item)" ng-confirmation>
     *              <span>强制退出</span>
     *            </a>
     */
    .directive('ngConfirmation',function () {
        return{
            restrict:'A',
            link:function (scope,elem,attrs) {
                var title = attrs.title;
                var groupname = attrs.groupname;

                var options = {
                    title: title,
                    placement: 'top',
                    singleton: true,
                    popout: true,
                    btnOkClass: 'btn-xs btn-primary',
                    btnOkIcon: 'glyphicon glyphicon-ok',
                    btnOkLabel: '确定',
                    btnCancelClass: 'btn-xs btn-default',
                    btnCancelIcon: 'glyphicon glyphicon-remove',
                    btnCancelLabel: '取消',
                    onConfirm: function () {
                        scope.$eval(attrs.onConfirm);
                    }
                };
                var rootSelectorOption = {
                    rootSelector: elem
                };

                if(groupname && groupname!=''){
                    rootSelectorOption = {
                        rootSelector: '[groupName='+groupname+']'
                    };
                }

                $(elem).confirmation($.extend(options,rootSelectorOption));
            }
        }
    })


    /*
     * angular directive ng-counterup
     *
     * @description jquery-counterup is a plugin of jquery for show the dynamic number, now I complied it with angular directive
     * @require jquery,jquery-counterup
     * @example  <span ng-counter-up="user.userAaBalance"></span>
     */
    .directive('ngCounterUp',function () {
        return{
            restrict:'A',
            scope:{
                ngCounterUp:'='
            },
            link:function (scope,elem,attrs) {
                scope.$watch('ngCounterUp',function (newValue,oldValue) {
                    if(typeof(newValue) != 'undefined' && newValue != null){
                        var attr =document.createAttribute("data-value");
                        attr.value= newValue;
                        elem.context.setAttributeNode(attr);
                        $(elem).counterUp({
                                delay: 30,
                                time: 1000
                        });
                    }
                });
            }
        }
    })

    /*
     * angular directive ng-bootstrap-selecter
     *
     * @description bootstrap-selecter is a plugin of bootstrap for show the selecter, now I complied it with angular directive
     * @require jquery,bootstrap-selecter
     * @example  <select ng-bootstrap-selecter="vm.allAABilType" class="form-control" ng-model="vm.newAaBillingItem.aaBilBillingType" ng-options="t as t.aaBilTypeName for t in vm.allAABilType"></select>
     */
    .directive('ngBootstrapSelecter',['$timeout',function ($timeout) {
        return{
            restrict:'A',
            scope:{
                ngBootstrapSelecter:'='
            },
            link:function (scope,elem,attrs) {
                var watch =  scope.$watch('ngBootstrapSelecter',function (newValue,oldValue) {
                    if(newValue!= null && typeof(newValue)!='undefined' && newValue != ""){
                        $(elem).selectpicker({
                            liveSearch: true
                        });
                        watch();
                    }
                });

            }
        }
    }])

    /*******************装饰器型指令End****************/




    /**************************************第三方插件指令End******************************************/










    /**************************************自定义指令******************************************/

    /*显示头像组件式指令 传入参数为attach(图片信息资料)
      当attach为null时使用默认图片--另一个传入参数defaultImgPath
      兼顾实时更新功能 当外部attach发生变化时，相继发生变化
      @example <ng-avatar default-img-path = '../statics/img/defaultAvatar.jpg' attach="{{item.userAttach}}"></ng-avatar>
     */
    .directive('ngAvatar',function () {
        return{
            restrict:'EA',
            scope:{
                defaultImgPath:'@',
                attach:'@'
            },
            template: function () {
                return '<img class="user-pic rounded" ng-src={{imgPath}}>';
            },
            link:function (scope) {
                scope.$watch('attach',function (newValue,oldValue ) {
                    if(newValue!=null && scope.attach!="") {
                        var attach = scope.$eval(newValue);
                        scope.imgPath = attach.attachFilePosition + "/" + attach.attachFileUuid + CONSTANT.FILEBOUNDSYMBOL + attach.attachFileName;
                    }
                    else{
                        scope.imgPath = scope.defaultImgPath;
                    }
                });
            }
        }
    })

    /*上传加显示头像组件式指令*/
    .directive('ngUploadAvatar',function () {
        return{
            restrict:'EA',
            scope: {
                defaultImgPath:'@',
                attach:'=',
                uploadPath:'@',
                closeFlag:'='
            },
            template: function () {
                return '<a title="点击更换头像" href="javascript:;" style="width:7%;float:left;background-size: 100% 100%;" class="img-circle">'
                         + '<input type="file" accept="image/*" style="width:100%;height: 36px;font-size:0px;opacity: 0;"/>'
                      + '</a>';
            },
            link:function (scope,elem,attrs) {
                var a = elem.find('a');
                var isUpload = false;                //是否上传的标志
                scope.$watch('attach',function (newValue,oldValue ) {
                    if(!isUpload) {
                        if (newValue != null && newValue != "") {
                            var imgPath = newValue.attachFilePosition + "/" + newValue.attachFileUuid + CONSTANT.FILEBOUNDSYMBOL + newValue.attachFileName;
                            a.css("background-image", "url(" + imgPath + ")");
                        } else
                            a.css("background-image", "url(" + scope.defaultImgPath + ")");
                    }
                });


                function input_onChange(){
                    a.find('input').on('change',function () {
                        var fileObj = this;
                        if(fileObj.files.length>0)
                        {
                            var allowExtension = ".jpg,.jpeg,.bmp,.gif,.png";
                            var fileExtension = fileObj.value.substring(fileObj.value.lastIndexOf(".")+1).toLowerCase();
                            var browserVersion = window.navigator.userAgent.toUpperCase();
                            if(allowExtension.indexOf(fileExtension)>-1){
                                if (fileObj.files) {//HTML5实现预览，兼容chrome、火狐7+等
                                    if (window.FileReader) {
                                        var reader = new FileReader();
                                        reader.onload = function (e) {
                                            a.css("background-image","url("+e.target.result+")");
                                        };
                                        reader.readAsDataURL(fileObj.files[0]);
                                    }
                                    else if (browserVersion.indexOf("SAFARI") > -1) {
                                        alert("不支持Safari6.0以下浏览器的图片预览!");
                                    }
                                }
                                else {
                                    alert("不支持H5以下浏览器的图片预览!");
                                }
                            }
                            else{
                                alert("仅支持" + allowExtension + "为后缀名的文件!");
                                fileObj.value = ""; //清空选中文件
                                if (browserVersion.indexOf("MSIE") > -1) {
                                    fileObj.select();
                                    document.selection.clear();
                                }
                                fileObj.outerHTML = fileObj.outerHTML;
                            }

                            var formData = new FormData();
                            formData.append('file', fileObj.files[0]);
                            formData.append('position', scope.uploadPath);
                            $.ajax({
                                url: '/upload',
                                type: 'POST',
                                cache: false,
                                data: formData,
                                processData: false,
                                contentType: false
                            }).success(function(res) {
                                scope.attach = res;
                                isUpload = true;
                                scope.$apply(scope.attach);
                            }).error(function(res) {
                                alert("上传头像文件失败！");
                            });
                            return fileObj.value;
                        }
                    });
                }
                input_onChange();

                scope.$watch('closeFlag',function (newValue,oldValue) {
                    if(newValue){
                        isUpload = false;
                        a.find('input').remove();
                        a.append("<input type='file' accept='image/*' style='width:100%;height: 36px;font-size:0px;opacity: 0;'/>");
                        input_onChange();
                    }
                });
            }
        }
    })

    /*有效性标签*/
    .directive('ngValid',function () {
        return{
            restrict:'E',
            scope:{
                valid:'='
            },
            template: function () {
                return '<button type="button" class="btn {{valid == 1?\'green\':\'default\'}} btn-sm">{{valid == 1?"有效":"无效"}}</button>';
            }
        }
    })
    /**************************************自定义指令******************************************/