package com.zy.service.impl;

import com.zy.model.TbBillingEntity;
import com.zy.model.TbUserEntity;
import com.zy.service.BillingService;
import com.zy.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/21.
 */
@Service("testService")
public class TestService {

    @Autowired
    BillingService billingService;

    public void test() throws Exception {

        String hql = "from TbBillingEntity as b where b.buser.uname =:uname";
        Map<String,String> map = new HashMap<String,String>();
        map.put("uname","周源");

        TbBillingEntity billingEntity = billingService.findUnique(hql,map);

        TbUserEntity userEntity = billingEntity.getBuser();
        System.out.println(userEntity.getUname());


    }
}
