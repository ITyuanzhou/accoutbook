package com.zy.service.impl;

import com.zy.dao.BaseDao;
import com.zy.service.BaseService;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * BaseServiceImpl 定义Service的通用操作的实现
 * Created by Administrator on 2017/6/23.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T>{

    @Autowired
    BaseDao<T> baseDao;



    /***********************************通用方法******************************************/

    public Serializable save(final T entity){
        return baseDao.save(entity);
    }
    public void update(final T entity){
        baseDao.update(entity);
    }
    public void saveOrUpdate(final T entity){
        baseDao.saveOrUpdate(entity);
    }

    public void delete(final T entity) {
        baseDao.delete(entity);
    }

    public void delete(final Serializable id) {
        baseDao.delete(id);
    }

    public T get(final Serializable id) {
        return baseDao.get(id);
    }

    /**
     * 按id列表获取对象列表.
     */
    public List<T> get(final Collection<Serializable> ids) {
        return baseDao.get(ids);
    }

    /**
     * 获取全部对象.
     */
    public List<T> getAll() {
        return baseDao.getAll();
    }

    /**
     * 获取全部对象, 支持按属性行序.
     */
    public List<T> getAll(String orderByProperty, boolean isAsc) {
        return baseDao.getAll(orderByProperty,isAsc);
    }

    /**
     * 按属性查找对象列表, 默认匹配方式为Like.
     */
    public List<T> findByProperty(final String propertyName, final Object value) {
        return baseDao.findByProperty(propertyName,value);
    }

    /**
     * 按属性查找唯一对象, 默认匹配方式为Like.
     */
    public T findUniqueByProperty(final String propertyName, final Object value) {
        return baseDao.findUniqueByProperty(propertyName,value);
    }

    /**
     * 按HQL查询对象列表.
     * @param <T>
     * @param values
     * 数量可变的参数,按顺序绑定.
     */
    public <T> List<T> find(final String hql, final Object... values) {
        return baseDao.find(hql,values);
    }

    /**
     * 按HQL查询对象列表.
     * @param values
     * 命名参数,按名称绑定.
     */
    public <T> List<T> find(final String hql, final Map<String, ?> values) {
        return baseDao.find(hql,values);
    }

    /**
     * 按HQL查询唯一对象.
     * @param values
     * 数量可变的参数,按顺序绑定.
     */
    public <T> T findUnique(final String hql, final Object... values) {
        return baseDao.findUnique(hql,values);
    }

    /**
     * 按HQL查询唯一对象.
     * @param values
     * 命名参数,按名称绑定.
     */
    public <T> T findUnique(final String hql, final Map<String, ?> values) {
        return baseDao.findUnique(hql,values);
    }

    /**
     * 执行HQL进行批量修改/删除操作.
     * @param values
     * 数量可变的参数,按顺序绑定.
     * @return 更新记录数.
     */
    public int batchExecute(final String hql, final Object... values) {
        return baseDao.batchExecute(hql, values);
    }

    /**
     * 执行HQL进行批量修改/删除操作.
     * @param values
     * 命名参数,按名称绑定.
     * @return 更新记录数.
     */
    public int batchExecute(final String hql, final Map<String, ?> values) {
        return baseDao.batchExecute(hql, values);
    }

    /**
     * 根据查询HQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
     * @param values
     * 数量可变的参数,按顺序绑定.
     */
    public Query createQuery(final String queryString, final Object... values) {
        return baseDao.createQuery(queryString,values);
    }

    /**
     * 根据查询HQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
     * @param values
     * 命名参数,按名称绑定.
     */
    public Query createQuery(final String queryString,
                             final Map<String, ?> values) {
        return baseDao.createQuery(queryString, values);
    }

    /**
     * 按Criteria查询对象列表.
     * @param criterions
     * 数量可变的Criterion.
     */
    public List<T> find(final Criterion... criterions) {
        return baseDao.find(criterions);
    }

    /**
     * 按Criteria查询对象列表.
     * @param detachedCriteria
     * 数量可变的Criterion.
     */
    public List<T> find(final DetachedCriteria detachedCriteria) {
        return baseDao.find(detachedCriteria);
    }

    /**
     * 按Criteria查询唯一对象.
     * @param criterions
     * 数量可变的Criterion.
     */
    public T findUnique(final Criterion... criterions) {
        return baseDao.findUnique(criterions);
    }

    /**
     * 按Criteria查询唯一对象.
     * @param detachedCriteria
     * 数量可变的Criterion.
     */
    public T findUnique(final DetachedCriteria detachedCriteria) {
        return baseDao.findUnique(detachedCriteria);
    }

    /**
     * 根据Criterion条件创建Criteria. 与find()函数可进行更加灵活的操作.
     * @param criterions
     * 数量可变的Criterion.
     */
    public Criteria createCriteria(final Criterion... criterions) {
        return baseDao.createCriteria(criterions);
    }

    /**
     * 根据Criterion条件创建Criteria. 与find()函数可进行更加灵活的操作.
     * @param detachedCriteria
     * 数量可变的Criterion.
     */
    public Criteria createCriteria(final DetachedCriteria detachedCriteria) {
        return baseDao.createCriteria(detachedCriteria);
    }

    /**
     * 初始化对象. 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化. 如果传入entity,
     * 则只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性. 如需初始化关联属性,需执行:
     * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
     * Hibernate.initialize
     * (user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
     */
    public void initProxyObject(Object proxy) {
        baseDao.initProxyObject(proxy);
    }

    /**
     * Flush当前Session.
     */
    public void flush() {
        baseDao.flush();
    }

    /**
     * 为Query添加distinct transformer. 预加载关联对象的HQL会引起主对象重复, 需要进行distinct处理.
     */
    public Query distinct(Query query) {
        return baseDao.distinct(query);
    }

    /**
     * 为Criteria添加distinct transformer. 预加载关联对象的HQL会引起主对象重复, 需要进行distinct处理.
     */
    public Criteria distinct(Criteria criteria) {
        return baseDao.distinct(criteria);
    }

    /**
     * 取得对象的主键名.
     */
    public String getIdName() {
        return baseDao.getIdName();
    }

    /**
     * 判断对象的属性值在数据库内是否唯一.
     * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
     */
    public boolean isPropertyUnique(final String propertyName,
                                    final Object newValue, final Object oldValue) {
        return baseDao.isPropertyUnique(propertyName, newValue, oldValue);
    }

}
