<%--
  Created by IntelliJ IDEA.
  User: ZJZL_HP
  Date: 2017/7/19
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="page-content-inner" style="margin-top: 3%;">
    <div class="mt-content-body">
        <div class="row">
            <div class="col-md-12 col-sm-12">
                <div class="portlet light ">
                    <div class="portlet-title">
                        <div class="caption caption-md">
                            <i class="icon-bar-chart font-dark hide"></i>
                            <span class="caption-subject font-green-steel bold uppercase">最近账单信息</span>
                            <span class="caption-helper"></span>
                        </div>
                        <div class="actions">
                            <div class="btn-group btn-group-devided" data-toggle="buttons">
                                <label id="today_lable" name="dateLabels"
                                       class="btn btn-transparent blue-oleo btn-no-border btn-outline btn-circle btn-sm active">
                                    <input type="radio" class="toggle">今天</label>
                                <label id="week_lable" name="dateLabels"
                                       class="btn btn-transparent blue-oleo btn-no-border btn-outline btn-circle btn-sm">
                                    <input type="radio" class="toggle">周</label>
                                <label id="month_lable" name="dateLabels"
                                       class="btn btn-transparent blue-oleo btn-no-border btn-outline btn-circle btn-sm">
                                    <input type="radio" class="toggle">月</label>
                            </div>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="">
                            <div id="today_pane">
                                <div class="row number-stats margin-bottom-30">
                                    <div class="col-md-6 col-sm-6 col-xs-6">
                                        <div class="stat-left">
                                            <div class="stat-chart">
                                                <!-- do not line break "sparkline_bar" div. sparkline chart has an issue when the container div has line break -->
                                                <div id="sparkline_bar">
                                                    <canvas width="113" height="55"
                                                            style="display: inline-block; width: 113px; height: 55px; vertical-align: top;"></canvas>
                                                </div>
                                            </div>
                                            <div class="stat-number">
                                                <div class="title"> 余额</div>
                                                <div class="number"><span>￥</span><span class="bold" ng-counter-up="user.userAaBalance"></span></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-sm-6 col-xs-6">
                                        <div class="stat-right">
                                            <div class="stat-chart">
                                                <!-- do not line break "sparkline_bar" div. sparkline chart has an issue when the container div has line break -->
                                                <div id="sparkline_bar2">
                                                    <canvas width="107" height="55"
                                                            style="display: inline-block; width: 107px; height: 55px; vertical-align: top;"></canvas>
                                                </div>
                                            </div>
                                            <div class="stat-number">
                                                <div class="title"> 最新交易笔数</div>
                                                <div style="text-align: center;" class="number" ng-bind="vm.newDetalAaBillingCount"></div>
                                            </div>
                                        </div>
                                        <div style="float: right;margin-top: 25px;">
                                            <a href="javascript:;" class="btn btn-circle btn-sm red" ng-click="vm.newBilling()"> 记一笔
                                                <i class="fa fa-edit"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-scrollable table-scrollable-borderless">
                                    <table class="table table-hover table-light">
                                        <thead>
                                        <tr class="uppercase">
                                            <th colspan="2"> 发起人</th>
                                            <th> 总额</th>
                                            <th colspan="2"> 类别</th>
                                            <th> 备注</th>
                                            <th> 人数</th>
                                            <th> 交易时间</th>
                                            <th> 实付款</th>
                                            <th> 操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr ng-repeat="item in vm.todayDetailAaBillingList">
                                            <td class="fit">
                                                <ng-avatar default-img-path = '../statics/img/defaultAvatar.jpg' attach="{{item.detailAaBilBilling.aaBilUser.userAttach}}"></ng-avatar>
                                            </td>
                                            <td>
                                                <a href="javascript:;" class="primary-link"
                                                   ng-bind="item.detailAaBilBilling.aaBilUser.userName"></a>
                                            </td>
                                            <td ng-bind="'￥' +item.detailAaBilBilling.aaBilTotalAmount"></td>
                                            <td class="fit">
                                                <ng-avatar default-img-path = '../statics/img/defaultBilType.jpg' attach="{{item.detailAaBilBilling.aaBilBillingType.aaBilTypeIconAttach}}"></ng-avatar>
                                            </td>
                                            <td ng-bind="item.detailAaBilBilling.aaBilBillingType.aaBilTypeName"></td>
                                            <td ng-bind="item.detailAaBilBilling.aaBilRemark"></td>
                                            <td ng-bind="item.detailAaBilBilling.aaBilPersonNumber"></td>
                                            <td ng-bind="item.detailAaBilBilling.aaBilTime"></td>
                                            <td>
                                                <span class="bold {{item.detailAaBilChargeState ==1 ? 'theme-font':'font-red'}}" ng-bind="'￥'+item.detailAaBilAmount"></span>
                                            </td>
                                            <td><button type="button" class="btn btn-sm btn-circle btn-outline sbold uppercase {{item.detailAaBilChargeState ==1 ? 'green-sharp':'purple-sharp'}}"
                                                  ng-bind="item.detailAaBilChargeState ==1 ? '取消支付':'确认支付'" ng-click="vm.togglePay(item)"></button>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="row number-stats margin-bottom-15">
                                    <div class="col-md-12 col-sm-12 col-xs-12" style="padding-right: 4%;">
                                        <a style="float: right;font-size: 8px;" ng-click="vm.loadThreeDaysBillings($event)">查看最近三天</a>
                                    </div>
                                </div>
                            </div>

                            <div id="week_pane" class="display-none">
                                <span>周</span>
                            </div>

                            <div id="month_pane" class="display-none">
                                <span>月</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%--记一笔模态框--%>
<div id="newAaBillingModal" class="modal fade" role="dialog" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">账单信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-body">
                        <div class="form-group {{vm.newAaBillingItem.aaBilBillingType!=null && typeof(vm.newAaBillingItem.aaBilBillingType)!='undefined'?'has-success':'has-error'}}">
                            <label class="col-md-3 control-label">账单类别</label>
                            <div class="col-md-7">
                                <select ng-bootstrap-selecter="vm.allAABilType" class="form-control" ng-model="vm.newAaBillingItem.aaBilBillingType"
                                       ng-options="t as t.aaBilTypeName for t in vm.allAABilType">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-body">
                        <div class="form-group {{vm.newAaBillingItem.aaBilTotalAmount>0?'has-success':'has-error'}}">
                            <label class="col-md-3 control-label">账单总金额</label>
                            <div class="col-md-7">
                                <input type="number" class="form-control" ng-model="vm.newAaBillingItem.aaBilTotalAmount" />
                            </div>
                        </div>
                    </div>
                    <div class="form-body">
                        <div class="form-group {{vm.newAaBillingItem.aaBilBearUserSet.length>0?'has-success':'has-error'}}">
                            <label class="col-md-3 control-label">账单AA人员</label>
                            <div class="col-md-7">
                                <select multiple data-actions-box="true" ng-bootstrap-selecter="vm.allUsers" class="form-control" ng-model="vm.newAaBillingItem.aaBilBearUserSet"
                                        ng-options="u as u.userName for u in vm.allUsers">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-body">
                        <div class="form-group has-success">
                            <label class="col-md-3 control-label">备注</label>
                            <div class="col-md-7">
                                <input class="form-control"  ng-model="vm.newAaBillingItem.aaBilRemark"></input>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-outline dark">Close</button>
                <button type="button" class="btn green" ng-click="vm.saveNewAaBilling(vm.newAaBillingItem)">Save</button>
            </div>
        </div>
    </div>
</div>
