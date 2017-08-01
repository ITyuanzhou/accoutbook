/*常量*/
var CONSTANT = {
    DATA_TABLES : {
        DEFAULT_OPTION : { //DataTables初始化选项
            pagingType: "full_numbers",//设置分页控件的模式
            pageLength: 5,
            bLengthChange: true,//tables的一页展示多少条记录的下拉列表
            aLengthMenu:[5,10,20,30],//设置一页展示10条记录
            language: {
                "sProcessing":   "处理中...",
                "sLengthMenu":   "每页 _MENU_ 项",
                "sZeroRecords":  "没有匹配结果",
                "sInfo":         "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
                "sInfoEmpty":    "当前显示第 0 至 0 项，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix":  "",
                "sSearch":       '<span class="label label-success">搜索：</span>',
                "sUrl":          "",
                "sEmptyTable":     "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands":  ",",
                "oPaginate": {
                    "sFirst":    "首页",
                    "sPrevious": "上页",
                    "sNext":     "下页",
                    "sLast":     "末页",
                    "sJump":     "跳转"
                },
                "oAria": {
                    "sSortAscending":  ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            autoWidth: false,	//禁用自动调整列宽
            stripeClasses: ["odd", "even"],//为奇偶行加上样式，兼容不支持CSS伪类的场合
            order: [],			//取消默认排序查询,否则复选框一列会出现小箭头
            processing: false,	//隐藏加载提示,自行处理
            deferRender: true, //打开延迟渲染
            serverSide: true,	//启用服务器端分页
            searching: true,	//禁用原生搜索
            stateSave: false, //保存最后一次分页信息、排序信息，当页面刷新，或者重新进入这个页面，恢复上次的状态
        },
        COLUMN: {
            CHECKBOX: {	//复选框单元格
                searchable: false,
                orderable: false,
                width: "5%",
                data: null,
                render: function (data, type, row, meta) {
                    return '<input type="checkbox" class="iCheck">';
                }
            },
            ORDERNUMBER:{
                searchable: false,
                orderable: false,
                data: null,
                width: "5%"
            },
            OPERATION: {	//操作框单元格
                searchable: false,
                orderable: false,
                width: "30%",
                data: null,
                render: function (data, type, row, meta) {
                    return '<button type="button" class="btn btn-small btn-primary btn-edit">修改</button>' +
                        '<button type="button" class="btn btn-small btn-danger btn-del">删除</button>';
                }
            },
            VIEW: {	//查看详情单元格
                searchable: false,
                orderable: false,
                width: "30%",
                data: null,
                render: function (data, type, row, meta) {
                    return  '<a class="btn dark btn-sm btn-outline sbold uppercase" name="view"><i class="fa fa-share"></i> View </a>';
                }
            },
            DOWN: {	//下载单元格
                searchable: false,
                orderable: false,
                width: "30%",
                data: null,
                render: function (data, type, row, meta) {
                    return  '<a class="btn dark btn-sm btn-outline sbold uppercase" name="down"><i class="fa fa-share"></i> Down </a>';
                }
            }
        },
        RENDER: {	//常用render可以抽取出来，如日期时间、头像等
            ELLIPSIS: function (data, type, row, meta) {
                data = data||"";
                return '<span title="' + data + '">' + data + '</span>';
            }
        }
    }
};
