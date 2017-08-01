var Login = function () {
    var initCookie = function () {
        //初始化cookie
        var accountbook_userName = getCookie('accountbook_userName');
        var accountbook_loginPwd = getCookie('accountbook_loginPwd');
        if(accountbook_userName != "" && accountbook_loginPwd != ""){
            $("#login_userName").val(accountbook_userName);
            $("#login_password").val(accountbook_loginPwd);
            $("#remember").attr("checked",'true');
            $("#remember").parents('.checkbox').find('span').addClass('checked');
        }
    };

    var handleLogin = function() {

        $('.login-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                },
                remember: {
                    required: false
                }
            },

            messages: {
                userName: {
                    required: "UserName is required."
                },
                password: {
                    required: "Password is required."
                },
            },

            invalidHandler: function(event, validator) { //display error alert on form submit
                //$('.alert-danger', $('.login-form')).show();
            },

            highlight: function(element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function(error, element) {
                if(element.attr("name") == "captcha"){
                    error.insertAfter($("#captchaDiv"));
                }else {
                    error.insertAfter(element.closest('.form-control'));
                }
            },

            submitHandler: function(form) {

            }
        });

        submitLogin = function () {
            $.ajax({
                    url:"/login/submitLogin",
                    type:"GET",
                    dataType:"json",
                    data: {                     //要传递的数据
                        userName: function () {
                            return $("#login_userName").val();
                        },
                        password: function () {
                            return $("#login_password").val();
                        }
                    },
                    success:function (res) {
                        if(res.flag>0){
                            if($('#remember').is(':checked')){
                                setCookie('accountbook_userName', $('#login_userName').val().trim(), 7)
                                setCookie('accountbook_loginPwd', $('#login_password').val().trim(), 7)
                            }else {
                                clearCookie('accountbook_userName');
                                clearCookie('accountbook_loginPwd');
                            }
                            window.location.href = "index";
                        }
                        else{
                            $("#login_error").text(res.message);
                            $('.alert-danger', $('.login-form')).show();
                        }
                    },
                    error:function (res) {
                        $('.alert-error', $('.login-form')).show();
                    }
                });
        };

        $('#login_alert_button').click(function () {
            $('.alert-danger', $('.login-form')).hide();
        });

        $('.login-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('.login-form').validate().form()) {
                    submitLogin();
                }
                return false;
            }
        });

        $("#loginBtn").click(function () {
            if ($('.login-form').validate().form()) {
                submitLogin();
            }
            return false;
        });
    };

    var handleForgetPassword = function() {

        $('.forget-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                email: {
                    required: true,
                    email: true,
                    checkEmailIsExist_forgetPwd:true
                }
            },

            messages: {
                email: {
                    required: "Email is required."
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit

            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function (error, element) {
                error.addClass('help-small no-left-padding').insertAfter(element.closest('.form-control'));
            },

            submitHandler: function (form) {

            }
        });

        submitForget = function () {
            $.ajax({
                url:"/forget/sendForgetEmail",
                type:"GET",
                dateType:"json",
                data: {                     //要传递的数据
                    email: function () {
                        return $("#forget_email").val();
                    }
                },
                success:function (res) {
                    alert("请等待邮件重置密码");
                },
                error:function (res) {
                }
            });
        };

        $('.forget-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('.forget-form').validate().form()) {
                    submitForget();
                }
                return false;
            }
        });

        jQuery('#forget_submit_btn').click(function () {
            if ($('.forget-form').validate().form()) {
                submitForget();
            }
            return false;
        });

        jQuery('#forget-password').click(function () {
            jQuery('.login-form').hide();
            jQuery('.forget-form').show();
        });

        jQuery('#back-btn').click(function () {
            jQuery('.login-form').show();
            jQuery('.forget-form').hide();
        });

    }

    var handleRegister = function() {
        $('.register-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                userID: {
                    required: true,
                    isUser8Number:true,
                    checkUserIDIsExist:true
                },
                roleID: {
                    required: true
                },
                password: {
                    required: true,
                    chrnum:true,
                    maxlength:15,
                    minlength:6
                },
                rpassword: {
                    equalTo: "#register_password"
                },
                email: {
                    required: true,
                    email: true,
                    checkEmailIsExist:true
                },
                tnc: {
                    required: true
                }
            },
            messages: { // custom messages for radio buttons and checkboxes
                tnc: {
                    required: "Please accept TNC first."
                }
            },
            invalidHandler: function (event, validator) { //display error alert on form submit
            },
            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },
            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },
            errorPlacement: function (error, element) {
                if (element.attr("name") == "tnc") { // insert checkbox errors after the container
                    error.addClass('help-small no-left-padding').insertAfter($('#register_tnc_error'));
                } else {
                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.form-control'));
                }
            },
            submitHandler: function (form) {
            }
        });

        submitRegister = function () {
            $.ajax({
                url:"/register/sendRegisterEmail",
                type:"GET",
                dateType:"json",
                data: {                     //要传递的数据
                    userID: function () {
                        return $("#register_userID").val();
                    },
                    roleID: function () {
                        return $("#register_roleID").val();
                    },
                    password: function () {
                        return hex_md5($("#register_password").val());
                    },
                    email: function () {
                        return $("#register_email").val();
                    }
                },
                success:function (res) {
                    alert("请等待邮件");
                },
                error:function (res) {
                }
            });
        }

        jQuery('#register-submit-btn').click(function () {
            if ($('.register-form').validate().form()) {
                submitRegister();
            }
            return false;
        });

        jQuery('#register-btn').click(function () {
            jQuery('.login-form').hide();
            jQuery('.register-form').show();
        });

        jQuery('#register-back-btn').click(function () {
            jQuery('.login-form').show();
            jQuery('.register-form').hide();
        });


    }

    var initBackImages = function () {
        // init background slide images
        $.backstretch([
            "../statics/assets/pages/media/bg/1.jpg",
            "../statics/assets/pages/media/bg/2.jpg",
            "../statics/assets/pages/media/bg/4.jpg",
            "../statics/assets/pages/media/bg/5.jpg"
        ], {
            fade: 1000,
            duration: 8000
        });
    }

    return {
        //main function to initiate the module
        init: function () {
            initCookie();
            handleLogin();
            handleForgetPassword();
            handleRegister();
            initBackImages();
        }
    };
}();

jQuery(document).ready(function() {
    Login.init();
});