<%--
  Created by IntelliJ IDEA.
  User: ZJZL_HP
  Date: 2017/7/19
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="profile-content" style="margin-top: 3%;">
    <div class="row">
        <div class="col-md-12">
            <div class="portlet light ">
                <div class="portlet-title tabbable-line">
                    <div class="caption caption-md">
                        <i class="icon-globe theme-font hide"></i>
                    </div>
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="/#tab_onlineUsers" data-toggle="tab" aria-expanded="true">在线用户统计</a>
                        </li>
                        <li class="">
                            <a href="/#tab_other" data-toggle="tab" aria-expanded="false">其他</a>
                        </li>
                        <li class="">
                            <a href="/#tab_deng" data-toggle="tab" aria-expanded="false">等等</a>
                        </li>
                    </ul>
                </div>
                <div class="portlet-body">
                    <div class="tab-content">
                        <!-- GENERAL QUESTION TAB -->
                        <div class="tab-pane active" id="tab_onlineUsers">
                            <div class="table-scrollable table-scrollable-borderless">
                                <table class="table table-hover table-light">
                                    <thead>
                                    <tr class="uppercase">
                                        <th colspan="2"> 用户</th>
                                        <th> 上线时间</th>
                                        <th> 操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr ng-repeat="item in vm.onlineUsersList">
                                        <td class="fit">
                                            <ng-avatar default-img-path = '../statics/img/defaultAvatar.jpg' attach="{{item.userAttach}}"></ng-avatar>
                                        </td>
                                        <td>
                                            <a href="javascript:;" class="primary-link" ng-bind="item.userName"></a>
                                        </td>
                                        <td ng-bind="item.userLastLoginTime"></td>
                                        <td>
                                            <a title="确定强制退出该用户？" groupname="logoutConfirm" on-confirm="vm.logoutConfirm(item)" ng-confirmation>
                                                <span>强制退出</span>
                                            </a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- END GENERAL QUESTION TAB -->
                        <!-- MEMBERSHIP TAB -->
                        <div class="tab-pane" id="tab_other">

                        </div>
                        <!-- END MEMBERSHIP TAB -->
                        <!-- TERMS OF USE TAB -->
                        <div class="tab-pane" id="tab_deng">

                        </div>
                        <!-- END TERMS OF USE TAB -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%--用户信息模态框--%>
<div id="userModal" class="modal fade" role="dialog" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <ng-upload-avatar default-img-path='../statics/img/defaultAvatar.jpg' attach='vm.userItem.userAttach' upload-path='/files/img/avatar' close-flag='vm.userModalCloseFlag'></ng-upload-avatar>
                <h4 class="modal-title" style="padding-top: 2%;margin-left: 8%;">用户信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-body">
                        <div class="form-group {{vm.userItem.userName.length>0?'has-success':'has-error'}}">
                            <label class="col-md-3 control-label">用户名</label>
                            <div class="col-md-7">
                                <input class="form-control"  ng-model="vm.userItem.userName"></input>
                            </div>
                        </div>
                        <div class="form-group {{vm.userItem.userPwd.length>0?'has-success':'has-error'}}">
                            <label class="col-md-3 control-label">用户密码</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" ng-model="vm.userItem.userPwd">
                            </div>
                        </div>
                        <div class="form-group {{vm.userItem.userPersonBalance.toString().length>0?'has-success':'has-error'}}">
                            <label class="col-md-3 control-label">个人账户余额</label>
                            <div class="col-md-7">
                                <input type="number" class="form-control" ng-model="vm.userItem.userPersonBalance">
                            </div>
                        </div>
                        <div class="form-group {{vm.userItem.userAaBalance.toString().length>0?'has-success':'has-error'}}">
                            <label class="col-md-3 control-label">AA账户余额</label>
                            <div class="col-md-7">
                                <input type="number" class="form-control" ng-model="vm.userItem.userAaBalance">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" ng-click="vm.closeUserModal()" class="btn btn-outline dark">Close</button>
                <button type="button" class="btn green" ng-click="vm.saveUser(vm.userItem)">Save</button>
            </div>
        </div>
    </div>
</div>

<%--账单类别信息模态框--%>
<div id="aaBilTypeModal" class="modal fade" role="dialog" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <ng-upload-avatar default-img-path='../statics/img/defaultBilType.jpg' attach='vm.aaBilTypeItem.aaBilTypeIconAttach' upload-path='/files/img/aaBilType' close-flag='vm.aaBilTypeModalCloseFlag'></ng-upload-avatar>
                <h4 class="modal-title" style="padding-top: 2%;margin-left: 8%;">账单类别信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-body">
                        <div class="form-group {{vm.aaBilTypeItem.aaBilTypeName.length>0?'has-success':'has-error'}}">
                            <label class="col-md-3 control-label">类别名称</label>
                            <div class="col-md-7">
                                <input class="form-control"  ng-model="vm.aaBilTypeItem.aaBilTypeName"></input>
                            </div>
                        </div>
                        <div class="form-group {{vm.aaBilTypeItem.aaBilTypeIsValid.toString().length>0?'has-success':'has-error'}}">
                            <label class="col-md-3 control-label">有效性</label>
                            <div class="col-md-7">
                                <ng-switch options="{onText:'有效',offText:'无效',onColor:'success',offColor:'default'}" ng-model="vm.aaBilTypeItem.aaBilTypeIsValid"></ng-switch>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" ng-click="vm.closeBilTypeModal()" class="btn btn-outline dark">Close</button>
                <button type="button" class="btn green" ng-click="vm.saveBillingType(vm.aaBilTypeItem)">Save</button>
            </div>
        </div>
    </div>
</div>
