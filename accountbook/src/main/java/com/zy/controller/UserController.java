package com.zy.controller;

import com.zy.model.TbUserEntity;
import com.zy.model.custom.Message;
import com.zy.service.UserService;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZJZL_HP on 2017/7/20.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET)
    public List<TbUserEntity> GetAllUsers(){
       return userService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    public Message SaveUser(@RequestBody TbUserEntity item){
        Message message = new Message();
        try{
            if(item.getUid() == null){
                String uid = userService.save(item).toString();
                Map map = new HashMap<Object, Object>();
                map.put("uid",uid);
                message.setMap(map);
            }
            else
                userService.update(item);
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    public Message DeleteUser(String uid){
        Message message = new Message();
        try{
            userService.delete(uid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }
}
