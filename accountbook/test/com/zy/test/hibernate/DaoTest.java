package com.zy.test.hibernate;

import com.zy.model.TbUserEntity;
import org.hibernate.Session;
import org.junit.Test;

/**
 * Created by Administrator on 2017/6/16.
 */
public class DaoTest {


    //增加
    @Test
    public void insert() {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();

            TbUserEntity user = new TbUserEntity();
            user.setUname("周源");
            session.save(user);

            session.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }
    //修改
    @Test
    public void update() {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();

            TbUserEntity user=(TbUserEntity)session.load(TbUserEntity.class, "8a7d2a9b5cafc760015cafc762000000");
            user.setUname("王一杰");
            session.update(user);
            session.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }
    //查找
    @Test
    public void getById() {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();

            TbUserEntity user=(TbUserEntity)session.load(TbUserEntity.class, "8a7d2a9b5cafc760015cafc762000000");
            System.out.println(user.getUid());
            System.out.println(user.getUname());
            session.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }
    //删除
    @Test
    public void delete() {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();

            TbUserEntity user=(TbUserEntity)session.load(TbUserEntity.class, "8a7d2a9b5cafc760015cafc762000000");

            session.delete(user);
            session.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }

}
