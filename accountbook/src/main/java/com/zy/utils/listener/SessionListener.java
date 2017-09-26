package com.zy.utils.listener;

import com.zy.model.TbUserEntity;
import com.zy.projectUtils.ProjectConstant;
import com.zy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by ZJZL_HP on 2017/9/14.
 */
public class SessionListener implements HttpSessionListener{

    @Override
    public void sessionCreated(HttpSessionEvent event) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        //在session销毁的时候 把loginUserMap中保存的键值对清除
        Object userKeyObject = event.getSession().getAttribute(ProjectConstant.Session_User_Key);
        if(userKeyObject!=null){
            String userKey = userKeyObject.toString();
            Map<String, HttpSession> loginUserMap = (Map<String, HttpSession>)event.getSession().getServletContext().getAttribute(ProjectConstant.Application_LoginUsersMap_Key);
            if(loginUserMap!= null && loginUserMap.containsKey(userKey)){
                loginUserMap.remove(userKey);
                event.getSession().getServletContext().setAttribute(ProjectConstant.Application_LoginUsersMap_Key,loginUserMap);
            }

            //记录用户的退出该系统的时间
            ApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(event.getSession().getServletContext());
            UserService userService = (UserService) application.getBean("userService");
            TbUserEntity user = userService.get(userKey);
            user.setUserLastLoginTime(new Timestamp(System.currentTimeMillis()));
            userService.save(user);
        }
    }
}
