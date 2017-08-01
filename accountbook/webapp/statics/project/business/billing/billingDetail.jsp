<%--
  Created by IntelliJ IDEA.
  User: ZJZL_HP
  Date: 2017/7/19
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="page-breadcrumb breadcrumb">
    <li>
        <a ui-sref="billingDetail">账单明细</a>
    </li>
</ul>

<div class="page-content-inner">
    <div class="mt-content-body">
        <div class="row">
            <div class="col-md-6 col-sm-6">
                <div class="portlet light ">
                    <div class="portlet-title">
                        <div class="caption caption-md">
                            <i class="icon-bar-chart font-dark hide"></i>
                            <span class="caption-subject font-green-steel uppercase bold">Sales Summary</span>
                            <span class="caption-helper hide">weekly stats...</span>
                        </div>
                        <div class="actions">
                            <div class="btn-group btn-group-devided" data-toggle="buttons">
                                <label class="btn btn-transparent btn-no-border blue-oleo btn-outline btn-circle btn-sm active">
                                    <input type="radio" name="options" class="toggle" id="option1">Today</label>
                                <label class="btn btn-transparent btn-no-border blue-oleo btn-outline btn-circle btn-sm">
                                    <input type="radio" name="options" class="toggle" id="option2">Week</label>
                                <label class="btn btn-transparent btn-no-border blue-oleo btn-outline btn-circle btn-sm">
                                    <input type="radio" name="options" class="toggle" id="option2">Month</label>
                            </div>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="row list-separated">
                            <div class="col-md-3 col-sm-3 col-xs-6">
                                <div class="font-grey-mint font-sm"> Total Sales </div>
                                <div class="uppercase font-hg font-red-flamingo"> 13,760
                                    <span class="font-lg font-grey-mint">$</span>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-6">
                                <div class="font-grey-mint font-sm"> Revenue </div>
                                <div class="uppercase font-hg theme-font"> 4,760
                                    <span class="font-lg font-grey-mint">$</span>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-6">
                                <div class="font-grey-mint font-sm"> Expenses </div>
                                <div class="uppercase font-hg font-purple"> 11,760
                                    <span class="font-lg font-grey-mint">$</span>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-6">
                                <div class="font-grey-mint font-sm"> Growth </div>
                                <div class="uppercase font-hg font-blue-sharp"> 9,760
                                    <span class="font-lg font-grey-mint">$</span>
                                </div>
                            </div>
                        </div>
                        <ul class="list-separated list-inline-xs hide">
                            <li>
                                <div class="font-grey-mint font-sm"> Total Sales </div>
                                <div class="uppercase font-hg font-red-flamingo"> 13,760
                                    <span class="font-lg font-grey-mint">$</span>
                                </div>
                            </li>
                            <li> </li>
                            <li class="border">
                                <div class="font-grey-mint font-sm"> Revenue </div>
                                <div class="uppercase font-hg theme-font"> 4,760
                                    <span class="font-lg font-grey-mint">$</span>
                                </div>
                            </li>
                            <li class="divider"> </li>
                            <li>
                                <div class="font-grey-mint font-sm"> Expenses </div>
                                <div class="uppercase font-hg font-purple"> 11,760
                                    <span class="font-lg font-grey-mint">$</span>
                                </div>
                            </li>
                            <li class="divider"> </li>
                            <li>
                                <div class="font-grey-mint font-sm"> Growth </div>
                                <div class="uppercase font-hg font-blue-sharp"> 9,760
                                    <span class="font-lg font-grey-mint">$</span>
                                </div>
                            </li>
                        </ul>
                        <div id="sales_statistics" class="portlet-body-morris-fit morris-chart" style="height: 267px; position: relative; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><svg height="267" version="1.1" width="455" xmlns="http://www.w3.org/2000/svg" style="overflow: hidden; position: relative; left: -0.5px;"><desc style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Created with Raphaël 2.1.2</desc><defs style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></defs><path fill="none" stroke="none" d="M0,267H455" stroke-width="0.5" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path><path fill="none" stroke="none" d="M0,200.25H455" stroke-width="0.5" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path><path fill="none" stroke="none" d="M0,133.5H455" stroke-width="0.5" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path><path fill="none" stroke="none" d="M0,66.75H455" stroke-width="0.5" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path><path fill="none" stroke="none" d="M0,0H455" stroke-width="0.5" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path><path fill="#e3f5f3" stroke="none" d="M0,106.80000000000001C28.592896174863387,109.025,85.77868852459017,119.03750000000002,114.37158469945355,115.70000000000002C142.96448087431693,112.36250000000001,200.1502732240437,78.98142076502735,228.7431693989071,80.10000000000002C257.0252732240437,81.20642076502735,313.5894808743169,127.38125,341.87158469945354,124.6C370.15368852459017,121.81875,426.71789617486337,74.53750000000002,455,57.85000000000002L455,267L0,267Z" fill-opacity="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); fill-opacity: 1;"></path><path fill="none" stroke="#92e9dc" d="M0,106.80000000000001C28.592896174863387,109.025,85.77868852459017,119.03750000000002,114.37158469945355,115.70000000000002C142.96448087431693,112.36250000000001,200.1502732240437,78.98142076502735,228.7431693989071,80.10000000000002C257.0252732240437,81.20642076502735,313.5894808743169,127.38125,341.87158469945354,124.6C370.15368852459017,121.81875,426.71789617486337,74.53750000000002,455,57.85000000000002" stroke-width="0" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path><circle cx="0" cy="106.80000000000001" r="0" fill="#92e9dc" stroke="#ffffff" stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle><circle cx="114.37158469945355" cy="115.70000000000002" r="0" fill="#92e9dc" stroke="#ffffff" stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle><circle cx="228.7431693989071" cy="80.10000000000002" r="0" fill="#92e9dc" stroke="#ffffff" stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle><circle cx="341.87158469945354" cy="124.6" r="0" fill="#92e9dc" stroke="#ffffff" stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle><circle cx="455" cy="57.85000000000002" r="0" fill="#92e9dc" stroke="#ffffff" stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle><path fill="#59aea2" stroke="none" d="M0,142.4C28.592896174863387,149.07500000000002,85.77868852459017,171.32500000000002,114.37158469945355,169.10000000000002C142.96448087431693,166.87500000000003,200.1502732240437,125.71857923497268,228.7431693989071,124.6C257.0252732240437,123.49357923497267,313.5894808743169,159.64374999999998,341.87158469945354,160.2C370.15368852459017,160.75625,426.71789617486337,136.8375,455,129.05L455,267L0,267Z" fill-opacity="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); fill-opacity: 1;"></path><path fill="none" stroke="#399a8c" d="M0,142.4C28.592896174863387,149.07500000000002,85.77868852459017,171.32500000000002,114.37158469945355,169.10000000000002C142.96448087431693,166.87500000000003,200.1502732240437,125.71857923497268,228.7431693989071,124.6C257.0252732240437,123.49357923497267,313.5894808743169,159.64374999999998,341.87158469945354,160.2C370.15368852459017,160.75625,426.71789617486337,136.8375,455,129.05" stroke-width="0" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path><circle cx="0" cy="142.4" r="0" fill="#399a8c" stroke="#ffffff" stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle><circle cx="114.37158469945355" cy="169.10000000000002" r="0" fill="#399a8c" stroke="#ffffff" stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle><circle cx="228.7431693989071" cy="124.6" r="0" fill="#399a8c" stroke="#ffffff" stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle><circle cx="341.87158469945354" cy="160.2" r="0" fill="#399a8c" stroke="#ffffff" stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle><circle cx="455" cy="129.05" r="0" fill="#399a8c" stroke="#ffffff" stroke-width="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></circle></svg> <div class="morris-hover morris-default-style" style="left: 363px; top: 34px; display: none;"><div class="morris-hover-row-label">2012 Q1</div><div class="morris-hover-point" style="color: #399a8c">
                            Sales:
                            1,550
                        </div><div class="morris-hover-point" style="color: #92e9dc">
                            Profit:
                            800
                        </div></div></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-sm-6">
                <div class="portlet light ">
                    <div class="portlet-title">
                        <div class="caption caption-md">
                            <i class="icon-bar-chart font-dark hide"></i>
                            <span class="caption-subject font-green-steel bold uppercase">Member Activity</span>
                            <span class="caption-helper">weekly stats...</span>
                        </div>
                        <div class="actions">
                            <div class="btn-group btn-group-devided" data-toggle="buttons">
                                <label class="btn btn-transparent blue-oleo btn-no-border btn-outline btn-circle btn-sm active">
                                    <input type="radio" name="options" class="toggle" id="option1">Today</label>
                                <label class="btn btn-transparent blue-oleo btn-no-border btn-outline btn-circle btn-sm">
                                    <input type="radio" name="options" class="toggle" id="option2">Week</label>
                                <label class="btn btn-transparent blue-oleo btn-no-border btn-outline btn-circle btn-sm">
                                    <input type="radio" name="options" class="toggle" id="option2">Month</label>
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
                                        <div class="title"> Total </div>
                                        <div class="number"> 2460 </div>
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
                                        <div class="title"> New </div>
                                        <div class="number"> 719 </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="table-scrollable table-scrollable-borderless">
                            <table class="table table-hover table-light">
                                <thead>
                                <tr class="uppercase">
                                    <th colspan="2"> MEMBER </th>
                                    <th> Earnings </th>
                                    <th> CASES </th>
                                    <th> CLOSED </th>
                                    <th> RATE </th>
                                </tr>
                                </thead>
                                <tbody><tr>
                                    <td class="fit">
                                        <img class="user-pic rounded" src="../assets/pages/media/users/avatar4.jpg"> </td>
                                    <td>
                                        <a href="javascript:;" class="primary-link">Brain</a>
                                    </td>
                                    <td> $345 </td>
                                    <td> 45 </td>
                                    <td> 124 </td>
                                    <td>
                                        <span class="bold theme-font">80%</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="fit">
                                        <img class="user-pic rounded" src="../assets/pages/media/users/avatar5.jpg"> </td>
                                    <td>
                                        <a href="javascript:;" class="primary-link">Nick</a>
                                    </td>
                                    <td> $560 </td>
                                    <td> 12 </td>
                                    <td> 24 </td>
                                    <td>
                                        <span class="bold theme-font">67%</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="fit">
                                        <img class="user-pic rounded" src="../assets/pages/media/users/avatar6.jpg"> </td>
                                    <td>
                                        <a href="javascript:;" class="primary-link">Tim</a>
                                    </td>
                                    <td> $1,345 </td>
                                    <td> 450 </td>
                                    <td> 46 </td>
                                    <td>
                                        <span class="bold theme-font">98%</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="fit">
                                        <img class="user-pic rounded" src="../assets/pages/media/users/avatar7.jpg"> </td>
                                    <td>
                                        <a href="javascript:;" class="primary-link">Tom</a>
                                    </td>
                                    <td> $645 </td>
                                    <td> 50 </td>
                                    <td> 89 </td>
                                    <td>
                                        <span class="bold theme-font">58%</span>
                                    </td>
                                </tr>
                                </tbody></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
