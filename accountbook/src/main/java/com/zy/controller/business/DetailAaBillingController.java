package com.zy.controller.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zy.model.TbAaBillingEntity;
import com.zy.model.TbDetailAaBillingEntity;
import com.zy.model.TbUserEntity;
import com.zy.model.custom.Message;
import com.zy.service.AABillingService;
import com.zy.service.DetailAaBillingService;
import com.zy.service.UserService;
import com.zy.utils.fastjsonUtil.FastJsonPropertyPreFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by ZJZL_HP on 2017/9/19.
 */
@Controller
@RequestMapping("/accountbook/detailAaBilling")
public class DetailAaBillingController {

    @Autowired
    UserService userService;

    @Autowired
    AABillingService aaBillingService;

    @Autowired
    DetailAaBillingService detailAaBillingService;

    @ResponseBody
    @RequestMapping(value = "/getDetailAaBillingList",method = RequestMethod.GET)
    public List<TbDetailAaBillingEntity> GetDetailAaBillingList(String userId,String time){
        List<TbDetailAaBillingEntity> list = null;
        TbUserEntity user = userService.get(userId);
        try {
            Timestamp ts = Timestamp.valueOf(time);
            list = detailAaBillingService.GetDetailAaBillingList(user,ts);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Map<Class<?>, String[]> map = new HashMap<Class<?>, String[]>();
//        map.put(TbDetailAaBillingEntity.class, new String[]{"detailAaBilAmount","detailAaBilChargeState","detailAaBilUser","detailAaBilBilling"});
//        map.put(TbUserEntity.class, new String[]{"userName","userPwd"});
//        map.put(TbAaBillingEntity.class, new String[]{"aaBilTotalAmount","aaBilTime"});
//
//        FastJsonPropertyPreFilter filter = new FastJsonPropertyPreFilter(map);
//        String str = JSONArray.toJSONString(list,filter);
//        JSON json = (JSON) JSON.parse(str);
//        return json;

        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/togglePay",method = RequestMethod.GET)
    public Message TogglePay(String detailAABilId,String userId){
       Message message = new Message();
       TbDetailAaBillingEntity detailAaBillingEntity = detailAaBillingService.get(detailAABilId);
       TbUserEntity user = userService.get(userId);

       if(detailAaBillingEntity.getDetailAaBilChargeState()==0){
           user.setUserAaBalance(user.getUserAaBalance() - detailAaBillingEntity.getDetailAaBilAmount());
           detailAaBillingEntity.setDetailAaBilChargeState(1);
       }else{
           user.setUserAaBalance(user.getUserAaBalance() + detailAaBillingEntity.getDetailAaBilAmount());
           detailAaBillingEntity.setDetailAaBilChargeState(0);
       }

       try{
           detailAaBillingService.update(detailAaBillingEntity);
           userService.update(user);

           String mes = detailAaBillingEntity.getDetailAaBilChargeState()==0?"取消支付成功":"支付成功";
           message.setMessage(mes);
       }catch (Exception e){
           message.setFlag(-1);
           message.setMessage("操作失败");
       }
       return message;
    }

    @ResponseBody
    @RequestMapping(value = "/saveNewAaBilling",method = RequestMethod.POST)
    public Message SaveNewAaBilling(@RequestBody TbAaBillingEntity item){
        Message message = new Message();

        Set<TbDetailAaBillingEntity> detailAaBillingEntitySet = new HashSet<TbDetailAaBillingEntity>();
        for (TbUserEntity userEntity:item.getAaBilBearUserSet()) {
            TbDetailAaBillingEntity detailAaBillingEntity = new TbDetailAaBillingEntity();
            detailAaBillingEntity.setDetailAaBilBilling(item);
            detailAaBillingEntity.setDetailAaBilUser(userEntity);
            detailAaBillingEntity.setDetailAaBilAmount(item.getAaBilTotalAmount()/item.getAaBilBearUserSet().size());
            detailAaBillingEntity.setDetailAaBilChargeState(0);

            detailAaBillingEntitySet.add(detailAaBillingEntity);
        }

        item.setDetailAaBillingEntitySet(detailAaBillingEntitySet);
        aaBillingService.save(item);
        return message;
    }

}
