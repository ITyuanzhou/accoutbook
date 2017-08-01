package com.zy.test.hibernate;

import com.zy.model.TbUserEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public class SixQueryTest {

    @Test
    /**
     * 常用方法，比较传统，类似jdbc。
     * HQL是hibernate自己的一套查询语言，于SQL语法不同，具有跨数据库的优点.
     * 缺点：新的查询语言，适用面有限，仅适用于Hibernate框架。
     */
    public void HQLQuery(){
        Session session = null;
        try {
            session = HibernateUtils.getSession();

            //from后面是对象，不是表名
            String hql = "from TbUserEntity as user where user.uname=:name";//使用命名参数，推荐使用，易读。
            Query query = session.createQuery(hql);
            query.setString("name", "周源");
            List<TbUserEntity> list= query.list();
            for(TbUserEntity user:list){
                System.out.println(user.getUname());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }


    @Test
    /**
     * 对象化查询Criteria方法
     * 面向对象操作，革新了以前的数据库操作方式，易读
     * 适用面较HQL有限
     */
    public void CriteriaQuery(){
        Session session = null;
        try {
            session = HibernateUtils.getSession();

            Criteria criteria = session.createCriteria(TbUserEntity.class);
            criteria.add(Restrictions.eq("name","周源"));
            List<TbUserEntity> list= criteria.list();
            for(TbUserEntity user:list){
                System.out.println(user.getUid()+" "+user.getUname());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Test
    /**
     * 动态分离查询DetachedCriteria
     * 面向对象操作，分离业务与底层，不需要字段属性摄入到Dao实现层。
     * 缺点：适用面较HQL有限。
     */
    public void DetachedCriteriaQuery(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbUserEntity.class);
        String name = "周源";
        if (name != null)
            detachedCriteria.add(Restrictions.eq("name", name));

        List<TbUserEntity> list = dc(detachedCriteria);
        for(TbUserEntity user:list){
            System.out.println(user.getUid()+" "+user.getUname());
        }
    }

    /**
     * 底层 在线执行DetachedCriteria
     * @param dc
     * @return
     */
    static List dc(DetachedCriteria dc) {
        Session session = null;
        List list = null;
        try{
            session = HibernateUtils.getSession();
            Criteria c = dc.getExecutableCriteria(session);
            list = c.list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
        return list;
    }

    @Test
    /**SQL 查询
     * 万能方法
     * 缺点：破坏跨平台，不易维护，不面向对象。
     */
    public void SQLQuery(){
        Session session = null;
        try{
            session = HibernateUtils.getSession();
            Query q = session.createSQLQuery("select * from tb_user").addEntity(TbUserEntity.class);
            List<TbUserEntity> list = q.list();
            for(TbUserEntity user:list){
                System.out.println(user.getUid()+" "+user.getUname());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }



}
