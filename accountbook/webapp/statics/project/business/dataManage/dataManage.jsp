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
                            <a href="/#tab_user" data-toggle="tab" aria-expanded="true">用户</a>
                        </li>
                        <li class="">
                            <a href="/#tab_billingType" data-toggle="tab" aria-expanded="false">账单类别</a>
                        </li>
                        <li class="">
                            <a href="/#tab_other" data-toggle="tab" aria-expanded="false">其他</a>
                        </li>
                    </ul>
                </div>
                <div class="portlet-body">
                    <div class="tab-content">
                        <!-- GENERAL QUESTION TAB -->
                        <div class="tab-pane active" id="tab_user">
                            <div class="table-scrollable table-scrollable-borderless">
                                <table class="table table-hover table-light" id="userManageTable">
                                    <thead>
                                    <tr class="uppercase">
                                        <th colspan="2"> 用户</th>
                                        <th> 密码</th>
                                        <th> 个人账户余额</th>
                                        <th> AA账户余额</th>
                                        <th> 最近一次登录时间</th>
                                        <th width="5%">
                                            <a href="javascript:;" ng-click="vm.addUser()">
                                                <i class="fa fa-plus"></i>
                                            </a>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr ng-repeat="item in vm.usersList">
                                        <td class="fit">
                                            <ng-avatar default-img-path = '../statics/img/defaultAvatar.jpg' attach="{{item.userAttach}}"></ng-avatar>
                                        </td>
                                        <td>
                                            <a href="javascript:;" class="primary-link" ng-bind="item.userName" ng-click="vm.editUser(item)"></a>
                                        </td>
                                        <td ng-bind="item.userPwd"></td>
                                        <td ng-bind="'￥'+item.userPersonBalance"></td>
                                        <td ng-bind="'￥'+item.userAaBalance"></td>
                                        <td ng-bind="item.userLastLoginTime"></td>
                                        <td>
                                            <a href="javascript:;" ng-click="vm.deleteUser(item)">
                                                <i class="fa fa-trash-o"></i>
                                            </a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- END GENERAL QUESTION TAB -->
                        <!-- MEMBERSHIP TAB -->
                        <div class="tab-pane" id="tab_billingType">
                            <div class="table-scrollable table-scrollable-borderless">
                                <table class="table table-hover table-light" id="aaBilTypeManageTable">
                                    <thead>
                                    <tr class="uppercase">
                                        <th colspan="2"> 类别</th>
                                        <th> 创建人</th>
                                        <th> 创建时间</th>
                                        <th> 有效性</th>
                                        <th width="5%">
                                            <a href="javascript:;" ng-click="vm.addBillingType()">
                                                <i class="fa fa-plus"></i>
                                            </a>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr ng-repeat="item in vm.aaBilTypeList">
                                        <td class="fit">
                                            <ng-avatar default-img-path = "../statics/img/defaultBilType.jpg" attach = "{{item.aaBilTypeIconAttach}}"></ng-avatar>
                                        </td>
                                        <td>
                                            <a href="javascript:;" class="primary-link" ng-bind="item.aaBilTypeName" ng-click="vm.editAABilType(item)"></a>
                                        </td>
                                        <td ng-bind="item.aaBilTypeCreUser.userName"></td>
                                        <td ng-bind="item.aaBilTypeCreTime"></td>
                                        <td><ng-valid valid="item.aaBilTypeIsValid"></ng-valid></td>
                                        <td>
                                            <a href="javascript:;" ng-click="vm.deleteAABilType(item)">
                                                <i class="fa fa-trash-o"></i>
                                            </a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- END MEMBERSHIP TAB -->
                        <!-- TERMS OF USE TAB -->
                        <div class="tab-pane" id="tab_other">

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
