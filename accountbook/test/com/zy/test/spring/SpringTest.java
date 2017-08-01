package com.zy.test.spring;

import com.zy.model.TbBillingEntity;
import com.zy.model.TbBillingTypeEntity;
import com.zy.model.TbUserEntity;
import com.zy.service.BillingService;
import com.zy.service.BillingTypeService;
import com.zy.service.UserService;
import com.zy.service.impl.TestService;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Administrator on 2017/6/21.
 */
public class SpringTest {

    //测试SessionFactory
    @Test
    public void testSessionFactory() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
        System.out.println(sessionFactory);
    }

    //测试hql查询
    @Test
    public void testHQLQuery() throws Exception{
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) ac.getBean("userService");

        String hql = "from TbUserEntity as user where user.uname=:name";
        Map<String,String> map = new HashMap<String, String>();
        map.put("name","周源");

        List<TbUserEntity> list = userService.find(hql,map);
        for (TbUserEntity userEntity:list) {
            System.out.println(userEntity.getUid()+" "+userEntity.getUname()+" "+userEntity.getUpersonBalance());
        }
    }

    //测试Criteria查询
    @Test
    public void testCriteriaQuery() throws Exception{
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) ac.getBean("userService");

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbUserEntity.class);
        detachedCriteria.add(Restrictions.like("uname", "周%"));

        List<TbUserEntity> list = userService.find(detachedCriteria);
        for (TbUserEntity userEntity:list) {
            System.out.println(userEntity.getUid()+" "+userEntity.getUname()+" "+userEntity.getUpersonBalance());
        }
    }

    //测试事务管理
    @Test
    public void txManager() throws Exception{
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        TestService testService = (TestService) ac.getBean("testService");
        testService.test();
    }

    @Test
    public void get() throws Exception{
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        TestService testService = (TestService) ac.getBean("testService");

        testService.test();

    }
}
