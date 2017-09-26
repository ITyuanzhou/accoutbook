package com.zy.controller.business;

import com.zy.model.TbAttachEntity;
import com.zy.model.TbUserEntity;
import com.zy.model.custom.Message;
import com.zy.projectUtils.ProjectConstant;
import com.zy.projectUtils.SaveAttach;
import com.zy.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZJZL_HP on 2017/7/20.
 */
@Controller
@RequestMapping("/accountbook/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    SessionFactory sessionFactory;


    @ResponseBody
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public TbUserEntity GetUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        return userService.get(session.getAttribute("userId").toString());
    }

    @ResponseBody
    @RequestMapping(value = "/getUserByUserId",method = RequestMethod.GET)
    public TbUserEntity GetUserByUserId(String userId){
        return userService.get(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET)
    public List<TbUserEntity> GetAllUsers(){
       return userService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    public Message SaveUser(HttpServletRequest request,@RequestBody TbUserEntity item){
        Message message = new Message();
        try{
            if(item.getUserId() == null){                            //新建
                SaveAttach.Save(request,item.getUserAttach());

                String userId = userService.save(item).toString();
                Map map = new HashMap<Object, Object>();
                map.put("userId",userId);
                message.setMap(map);
            }
            else{                                                    //更新
                TbUserEntity oldUser = userService.get(item.getUserId());
                SaveAttach.Update(request,oldUser.getUserAttach(),item.getUserAttach());

                sessionFactory.getCurrentSession().evict(oldUser);
                userService.update(item);
            }
        }catch (Exception e){
            message.setFlag(0);
            message.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    public Message DeleteUser(HttpServletRequest request,String uid){
        Message message = new Message();
        try{
            TbUserEntity deleteUser = userService.get(uid);

            if(deleteUser!=null){
                TbAttachEntity deleteAttach = deleteUser.getUserAttach();
                userService.delete(uid);
                if(deleteAttach != null)
                    SaveAttach.DeleteOldAttach(request,deleteAttach);
            }
        }catch (Exception e){
            message.setFlag(0);
            message.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public Message Logout(HttpServletRequest request){
        Message message = new Message();
        try{
          HttpSession session =  request.getSession(false);
          if(session!=null)
             session.invalidate();
        }catch (Exception e){
            message.setFlag(0);
            message.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/logoutByUserId",method = RequestMethod.GET)
    public Message LogoutByUserId(HttpServletRequest request,String userId){
        Message message = new Message();
        try{
            ServletContext application = request.getSession().getServletContext();

            Map<String, HttpSession> loginUserMap = (Map<String, HttpSession>) application.getAttribute(ProjectConstant.Application_LoginUsersMap_Key);
            if(loginUserMap != null && userId != null && loginUserMap.containsKey(userId)){
                HttpSession session =  loginUserMap.get(userId);
                if(session!=null)
                   session.invalidate();
            }
            else{
                message.setFlag(0);
                message.setMessage("该用户已经下线！");
            }
        }catch (Exception e){
            message.setFlag(0);
            message.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/getOnlineUsers",method = RequestMethod.GET)
    public List<TbUserEntity> GetOnlineUsers(HttpServletRequest request){
        List<TbUserEntity> onlineUsersList = new ArrayList<TbUserEntity>();
        ServletContext application = request.getSession().getServletContext();
        Map<String, HttpSession> loginUserMap = (Map<String, HttpSession>) application.getAttribute(ProjectConstant.Application_LoginUsersMap_Key);
        if(loginUserMap != null){
            for (String userId:loginUserMap.keySet()) {
                TbUserEntity user = userService.get(userId);
                onlineUsersList.add(user);
            }
        }
        return onlineUsersList;
    }
}
