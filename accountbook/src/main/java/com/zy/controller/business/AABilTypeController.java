package com.zy.controller.business;

import com.zy.model.TbAaBillingTypeEntity;
import com.zy.model.TbAttachEntity;
import com.zy.model.TbUserEntity;
import com.zy.model.custom.Message;
import com.zy.projectUtils.SaveAttach;
import com.zy.service.AABillingTypeService;
import com.zy.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZJZL_HP on 2017/8/17.
 */
@Controller
@RequestMapping("/accountbook/aaBilType")
public class AABilTypeController {
    @Autowired
    AABillingTypeService aaBillingTypeService;
    @Autowired
    UserService userService;
    @Autowired
    SessionFactory sessionFactory;

    @ResponseBody
    @RequestMapping(value = "/getAllAABilType",method = RequestMethod.GET)
    public List<TbAaBillingTypeEntity> GetAllAABilType(){
        return aaBillingTypeService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/saveAABilType",method = RequestMethod.POST)
    public Message SaveAABilType(@RequestBody TbAaBillingTypeEntity item,HttpServletRequest request){
        String userId = request.getSession().getAttribute("userID").toString();
        Message message = new Message();
        try{
            if(item.getAaBilTypeId() == null){                         //新增
                SaveAttach.Save(request,item.getAaBilTypeIconAttach());

                TbUserEntity tbUser = userService.get(userId);
                item.setAaBilTypeCreUser(tbUser);
                Timestamp aaBilTypeCreTime =  new Timestamp(System.currentTimeMillis());
                item.setAaBilTypeCreTime(aaBilTypeCreTime);

                String aaBilTypeId = aaBillingTypeService.save(item).toString();

                Map map = new HashMap<Object, Object>();
                map.put("aaBilTypeId",aaBilTypeId);
                map.put("aaBilTypeCreUser",tbUser);
                map.put("aaBilTypeCreTime",aaBilTypeCreTime);
                message.setMap(map);
            }
            else{                                                     //更新
                TbAaBillingTypeEntity oldBilType = aaBillingTypeService.get(item.getAaBilTypeId());
                SaveAttach.Update(request,oldBilType.getAaBilTypeIconAttach(),item.getAaBilTypeIconAttach());

                sessionFactory.getCurrentSession().evict(oldBilType);
                aaBillingTypeService.update(item);
            }
        }catch (Exception e){
            message.setFlag(0);
            message.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAABilType",method = RequestMethod.GET)
    public Message DeleteAABilType(HttpServletRequest request,String aaBilTypeId){
        Message message = new Message();
        try{
            TbAaBillingTypeEntity deleteBilType = aaBillingTypeService.get(aaBilTypeId);
            if(deleteBilType!=null){
                TbAttachEntity deleteAttach = deleteBilType.getAaBilTypeIconAttach();

                aaBillingTypeService.delete(aaBilTypeId);

                if(deleteAttach!=null)
                    SaveAttach.DeleteOldAttach(request,deleteAttach);
            }

        }catch (Exception e){
            message.setFlag(0);
            message.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return message;
    }
}
