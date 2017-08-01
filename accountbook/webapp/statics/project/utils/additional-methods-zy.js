/**
 * Created by Administrator on 2017/3/22.
 */
/*!
 * jQuery Validation Plugin v1.14.0
 *
 * http://jqueryvalidation.org/
 *
 * Copyright (c) 2015 Jörn Zaefferer
 * Released under the MIT license
 */
(function( factory ) {
    if ( typeof define === "function" && define.amd ) {
        define( ["jquery", "./jquery.validate"], factory );
    } else {
        factory( jQuery );
    }
}(function( $ ) {

//学号只能是8位数字
jQuery.validator.addMethod("isUser8Number",function (value,element) {
    return this.optional(element) || (value.length == 8 && !isNaN(Number(value)));
},"只能输入8位数字");

//检查学号是否存在
jQuery.validator.addMethod("checkUserIDIsExist",function (value,element) {
    var userID = value;
    var result = true;
    $.ajax({
        type:"GET",
        async:false,
        url:"/register/checkUserIDIsExist",
        data:{userID: userID},
        success:function(response){
          if(response)
              result = false;   //responser =true 就是存在  result设为false 不让其通过
          else
              result = true;
            }
    });
    return this.optional(element) || result;
  },"该用户名已被注册");

// 字母和数字的验证
jQuery.validator.addMethod("chrnum", function(value, element) {
    var chrnum = /^([a-zA-Z0-9]+)$/;
    return this.optional(element) || (chrnum.test(value));
}, "只能输入数字和字母");

//检查邮箱是否被注册了
jQuery.validator.addMethod("checkEmailIsExist",function (value,element) {
    var email = value;
    var result = true;
    $.ajax({
        type:"GET",
        async:false,
        url:"/register/checkEmailIsExist",
        data:{email: email},
        success:function(response){
            if(response)
                result = false;
            else
                result = true;
        }
    });
    return this.optional(element) || result;
},"该邮箱已被绑定");

//检查邮箱是否被注册了(忘记密码界面)
jQuery.validator.addMethod("checkEmailIsExist_forgetPwd",function (value,element) {
    var email = value;
    var result = true;
    $.ajax({
        type:"GET",
        async:false,
        url:"/forget/checkEmailIsExist_forget",
        data:{email: email},
        success:function(response){
                result = response;
        }
    });
    return this.optional(element) || result;
},"该邮箱还未注册激活");

}));