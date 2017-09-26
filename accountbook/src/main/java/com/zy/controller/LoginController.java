package com.zy.controller;

import com.zy.model.TbUserEntity;
import com.zy.model.custom.Message;
import com.zy.projectUtils.ProjectConstant;
import com.zy.service.UserService;
import org.aspectj.apache.bcel.classfile.Constant;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/26.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/submitLogin",method = RequestMethod.GET)
    public Message SubmitLogin(String userName, String password, HttpServletRequest request){
        Message message = new Message();

        Criterion[] criterions = new Criterion[2];
        criterions[0] = Restrictions.eq("userName",userName);
        criterions[1] = Restrictions.eq("userPwd",password);
        TbUserEntity userEntity = userService.findUnique(criterions);

        if(userEntity == null){
            message.setFlag(-1);
            message.setMessage("用户名或者密码错误");
        }else {
            //密码正确后要验证此账户是否已经登录
            String userKey = userEntity.getUserId();
            handleUserExistInApplication(request,userKey);
        }
        return message;
    }

    //密码正确后处理账户是否已经登录
    private void handleUserExistInApplication(HttpServletRequest request,String userKey){
        boolean isExist = false;
        boolean isOneSession = false;    //是否是同一个用户重复登录
        ServletContext application = request.getSession().getServletContext();
        HttpSession session = request.getSession();
        Map<String, HttpSession> loginUserMap = (Map<String, HttpSession>) application.getAttribute(ProjectConstant.Application_LoginUsersMap_Key);
        if(loginUserMap == null)
            loginUserMap = new HashMap<String, HttpSession>();

        for (String loginUserKey : loginUserMap.keySet()) {
            if(loginUserKey.equals(userKey)){
                isExist = true;
                if(session == loginUserMap.get(loginUserKey)){
                    isExist = false;
                    isOneSession = true;
                }
                break;
            }
        }

        //如果用户存在且不是同一个session --异地登录 挤掉原来的session
        if(isExist && !isOneSession){
            HttpSession oldSession = loginUserMap.get(userKey);
            if(oldSession != null)
                oldSession.invalidate();
        }

        //如果不是同一个session，添加用户信息到Application中
        if(!isOneSession){
            session.setAttribute(ProjectConstant.Session_User_Key,userKey);
            loginUserMap.put(userKey, session);
            application.setAttribute(ProjectConstant.Application_LoginUsersMap_Key,loginUserMap);
        }
    }
}
