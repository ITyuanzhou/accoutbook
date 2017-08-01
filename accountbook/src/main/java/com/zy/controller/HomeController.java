package com.zy.controller;

import com.zy.model.TbDetailAaBillingEntity;
import com.zy.model.TbUserEntity;
import com.zy.service.DetailAaBillingService;
import com.zy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/22.
 */
@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    DetailAaBillingService detailAaBillingService;

    @RequestMapping("/")
    public String Login(){
        return "login/login";
    }

    @RequestMapping("/index")
    public ModelAndView Index(HttpServletRequest request){
        String userID = request.getSession().getAttribute("userID").toString();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index/index");

        TbUserEntity userInfo = userService.get(userID);
        modelAndView.getModel().put("userInfo",userInfo);

        String hql = "from TbDetailAaBillingEntity as d where d.duser.uid =:uid and d.dbilling.btime>:btime";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("uid",userID);
        map.put("btime",userInfo.getUlastLoginTime());
        List<TbDetailAaBillingEntity> detailAaBillingEntity = detailAaBillingService.find(hql,map);

//        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbDetailBillingEntity.class);
//        detachedCriteria.add(Restrictions.eq("dbilling.bremark",userID));
//        List<TbDetailBillingEntity> detailBillingEntityList = detailBillingService.find(detachedCriteria);


        return modelAndView;
    }
}
