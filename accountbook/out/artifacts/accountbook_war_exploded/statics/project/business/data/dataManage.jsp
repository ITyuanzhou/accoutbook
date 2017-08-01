<%--
  Created by IntelliJ IDEA.
  User: ZJZL_HP
  Date: 2017/7/19
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="profile-content">
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
                                            <img class="user-pic rounded"
                                                 ng-src={{item.uattach.afilePosition+"/"+item.uattach.afileUuid+item.uattach.afileName}}>
                                        </td>
                                        <td>
                                            <a href="javascript:;" class="primary-link" ng-bind="item.uname" ng-click="vm.editUser(item)"></a>
                                        </td>
                                        <td ng-bind="item.upwd"></td>
                                        <td ng-bind="'￥'+item.upersonBalance"></td>
                                        <td ng-bind="'￥'+item.uaaBalance"></td>
                                        <td ng-bind="item.ulastLoginTime"></td>
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


<%--新增模态框--%>
<div id="newUserModal" class="modal fade" role="dialog" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <a id="a_avatar" alt="" href="javascript:;" style="width:6%;padding-top: 2%;float:left;display:inline-block;background-image: url(statics/img/defaultAvatar.jpg);background-size: 100% 100%;" class="img-circle">
                    <input id="avatarFile" type="file" accept="image/*" style="width:100%;height: 100%;opacity: 0;" onchange="angular.element(this).scope().fileNameChanged(this)"/>
                </a>
                <h4 class="modal-title" style="padding-top: 2%;margin-left: 8%;">新增用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-body">
                        <div class="form-group {{vm.userItem.uname.length>0?'has-success':'has-error'}}">
                            <label class="col-md-3 control-label">用户名</label>
                            <div class="col-md-7">
                                <input class="form-control"  ng-model="vm.userItem.uname"></input>
                            </div>
                        </div>
                        <div class="form-group {{vm.userItem.upwd.length>0?'has-success':'has-error'}}">
                            <label class="col-md-3 control-label">用户密码</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" ng-model="vm.userItem.upwd">
                            </div>
                        </div>
                        <div class="form-group {{vm.userItem.upersonBalance.toString().length>0?'has-success':'has-error'}}">
                            <label class="col-md-3 control-label">个人账户余额</label>
                            <div class="col-md-7">
                                <input type="number" class="form-control" ng-model="vm.userItem.upersonBalance">
                            </div>
                        </div>
                        <div class="form-group {{vm.userItem.uaaBalance.toString().length>0?'has-success':'has-error'}}">
                            <label class="col-md-3 control-label">AA账户余额</label>
                            <div class="col-md-7">
                                <input type="number" class="form-control" ng-model="vm.userItem.uaaBalance">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-outline dark">Close</button>
                <button type="button" class="btn green" ng-click="vm.saveUser(vm.userItem)">Save</button>
            </div>
        </div>
    </div>
</div>
