package com.zy.service.impl;

import com.zy.model.TbAaBillingEntity;
import com.zy.model.TbUserEntity;
import com.zy.service.AABillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/21.
 */
@Service("testService")
public class TestService {

    @Autowired
    AABillingService billingService;

    public void test() throws Exception {

        String hql = "from TbBillingEntity as b where b.buser.uname =:uname";
        Map<String,String> map = new HashMap<String,String>();
        map.put("uname","周源");

        TbAaBillingEntity billingEntity = billingService.findUnique(hql,map);

        TbUserEntity userEntity = billingEntity.getAaBilUser();
        System.out.println(userEntity.getUserName());


    }
}
