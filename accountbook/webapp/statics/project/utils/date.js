/**
 * Created by ZJZL_HP on 2017/9/22.
 * 关于时间的工具类
 */

//转换成后台TimeStam类与Stirng字符串互转需要的格式 yyyy-mm-dd hh:mm:ss[.f...] 括号表示可选
function format(now) {
    var year = now.getFullYear();
    var month = (now.getMonth()+1)<10 ? '0'+(now.getMonth()+1) : now.getMonth()+1;
    var date = now.getDate()<10 ? '0'+now.getDate() : now.getDate();
    var hour = now.getHours()<10 ? '0'+now.getHours() : now.getHours();
    var minute = now.getMinutes()<10 ? '0'+now.getMinutes() : now.getMinutes();
    var second = now.getSeconds()<10 ? '0'+now.getSeconds() : now.getSeconds();
    var millSecond =  now.getMilliseconds();

    if(millSecond <10)
        millSecond = '0' + millSecond;
    if(millSecond <100)
        millSecond = '0' + millSecond;

    return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second+"."+millSecond;
}

function zeroDate(now) {
    now.setHours(0);
    now.setMinutes(0);
    now.setSeconds(0);
    now.setMilliseconds(0);
    return format(now);
}




function todayDate() {
    return zeroDate(new Date());
}

function thisWeekDate() {
    var date = new Date();
    var time = date.getTime();

    //按周日为一周的最后一天计算
    //今天是这周的第几天
    var today = date.getDay();

    //上周日距离今天的天数（负数表示）
    var stepSunDay = -today + 1;
    // 如果今天是周日
    if (today == 0)
        stepSunDay = -7;


    // 周一距离今天的天数（负数表示）
    //var stepMonday = 7 - today;

    var monday = new Date(time + stepSunDay * 24 * 3600 * 1000);
    //var sunday = new Date(time + stepMonday * 24 * 3600 * 1000);

    return zeroDate(monday);
}

function thisMonthDate() {
    // 获取当前月的第一天
    var start = new Date();
    start.setDate(1);

    // // 获取当前月的最后一天
    // var date = new Date();
    // var currentMonth = date.getMonth();
    // var nextMonth = ++currentMonth;
    // var nextMonthFirstDay = new Date(date.getFullYear(), nextMonth, 1);
    // var oneDay = 1000 * 60 * 60 * 24;
    // var end = new Date(nextMonthFirstDay - oneDay);
    return zeroDate(start);
}




function threeDaysAgoDate(now) {
    return format(new Date(now.getTime() - 3 * 24 * 3600 * 1000));
}

function weekAgoDate(now) {
    return format(new Date(now.getTime() - 7 * 24 * 3600 * 1000));
}

function monthAgoDate(now) {
    return format(new Date(now.getTime() - 30 * 24 * 3600 * 1000));
}