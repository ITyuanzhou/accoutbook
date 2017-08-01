package com.zy.controller;

import com.zy.model.TbUserEntity;
import com.zy.model.custom.Message;
import com.zy.service.UserService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

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
        criterions[0] = Restrictions.eq("uname",userName);
        criterions[1] = Restrictions.eq("upwd",password);
        TbUserEntity userEntity = userService.findUnique(criterions);

        if(userEntity == null){
            message.setFlag(-1);
            message.setMessage("用户名或者密码错误");
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("userID",userEntity.getUid());
            userEntity.setUlastLoginTime(new Timestamp(System.currentTimeMillis()));
            userService.save(userEntity);
        }
        return message;
    }
}
