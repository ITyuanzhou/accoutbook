<%--
  Created by IntelliJ IDEA.
  User: ZJZL_HP
  Date: 2017/7/19
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<ul class="page-breadcrumb breadcrumb">
    <li>
        <a ui-sref="home">主页</a>
    </li>
</ul>

<div class="page-content-inner">
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
                                <label class="btn btn-transparent blue-oleo btn-no-border btn-outline btn-circle btn-sm active">
                                    <input type="radio" name="options" class="toggle" id="option1">今天</label>
                                <label class="btn btn-transparent blue-oleo btn-no-border btn-outline btn-circle btn-sm">
                                    <input type="radio" name="options" class="toggle" id="option2">周</label>
                                <label class="btn btn-transparent blue-oleo btn-no-border btn-outline btn-circle btn-sm">
                                    <input type="radio" name="options" class="toggle" id="option2">月</label>
                            </div>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="row number-stats margin-bottom-30">
                            <div class="col-md-6 col-sm-6 col-xs-6">
                                <div class="stat-left">
                                    <div class="stat-chart">
                                        <!-- do not line break "sparkline_bar" div. sparkline chart has an issue when the container div has line break -->
                                        <div id="sparkline_bar"><canvas width="113" height="55" style="display: inline-block; width: 113px; height: 55px; vertical-align: top;"></canvas></div>
                                    </div>
                                    <div class="stat-number">
                                        <div class="title"> 余额 </div>
                                        <div class="number"> 100 </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-sm-6 col-xs-6">
                                <div class="stat-right">
                                    <div class="stat-chart">
                                        <!-- do not line break "sparkline_bar" div. sparkline chart has an issue when the container div has line break -->
                                        <div id="sparkline_bar2"><canvas width="107" height="55" style="display: inline-block; width: 107px; height: 55px; vertical-align: top;"></canvas></div>
                                    </div>
                                    <div class="stat-number">
                                        <div class="title"> 最新交易笔数 </div>
                                        <div class="number"> 2 </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="table-scrollable table-scrollable-borderless">
                            <table class="table table-hover table-light">
                                <thead>
                                <tr class="uppercase">
                                    <th colspan="2"> 发起人 </th>
                                    <th> 总额 </th>
                                    <th> 类别 </th>
                                    <th> 备注 </th>
                                    <th> 人数 </th>
                                    <th> 交易时间 </th>
                                    <th> 实付款 </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-repeat="item in vm.billingDetailList">
                                    <td class="fit">
                                        <img class="user-pic rounded" src="../statics/assets/pages/media/users/avatar4.jpg"> </td>
                                    <td>
                                        <a href="javascript:;" class="primary-link" ng-bind="item.originator"></a>
                                    </td>
                                    <td ng-bind="'￥' +item.btotalAmount"></td>
                                    <td ng-bind="item.btypeName"></td>
                                    <td ng-bind="item.bremark"></td>
                                    <td ng-bind="item.personNumber"></td>
                                    <td ng-bind="item.chargeTime"></td>
                                    <td>
                                        <span class="bold theme-font" ng-bind="'￥'+item.chargeAmount"></span>
                                    </td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
