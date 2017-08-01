package com.zy.dao.impl;

import com.zy.dao.BaseDao;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * BaseDaoImpl 定义DAO的通用操作的实现
 * Created by Administrator on 2017/6/23.
 */

public class BaseDaoImpl<T> implements BaseDao<T>{

    protected Logger logger = Logger.getLogger(getClass());

    private Class<T> clazz;

    @Autowired
    SessionFactory sessionFactory;

    /**
     * 通过构造方法指定DAO的具体实现类
     */
    public BaseDaoImpl() {
        Type type =  this.getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType)type;
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
        System.out.println("DAO的真实实现类的泛型是：" + this.clazz.getName());
    }

    /**
     * 用于用于省略Dao层, 在Service层直接使用通用SimpleHibernateDao的构造函数. 在构造函数中定义对象类型Class.
     * eg. BaseDaoImpl<User, Long> userDao = new BaseDaoImpl<User,
     * Long>(sessionFactory, User.class);
     */
    public BaseDaoImpl(final SessionFactory sessionFactory,
                              final Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    /**
     * 取得sessionFactory.
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * 取得当前Session.
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    /***********************************通用方法******************************************/
    public Serializable save(final T entity){
        logger.info("save entity: {}");
        logger.info(entity);
        return getSession().save(entity);
    }

    public void update(final T entity){
        getSession().saveOrUpdate(entity);
        logger.info("update entity: {}");
        logger.info(entity);
    }

    public void saveOrUpdate(final T entity){
        getSession().saveOrUpdate(entity);
        logger.info("saveOrUpdate entity: {}");
        logger.info(entity);
    }

    public void delete(final T entity) {
        getSession().delete(entity);
        logger.info("delete entity: {}");
        logger.info(entity);
    }

    public void delete(final Serializable id) {
        delete(get(id));
        logger.info("delete entity {},id is {}");
        logger.info(clazz.getSimpleName());
        logger.info(id);
    }

    public T get(final Serializable id) {
        return (T) getSession().load(clazz, id);
    }

    /**
     * 按id列表获取对象列表.
     */
    public List<T> get(final Collection<Serializable> ids) {
        return find(Restrictions.in(getIdName(), ids));
    }

    /**
     * 获取全部对象.
     */
    public List<T> getAll() {
        return find();
    }

    /**
     * 获取全部对象, 支持按属性行序.
     */
    public List<T> getAll(String orderByProperty, boolean isAsc) {
        Criteria c = createCriteria();
        if (isAsc) {
            c.addOrder(Order.asc(orderByProperty));
        } else {
            c.addOrder(Order.desc(orderByProperty));
        }
        return c.list();
    }

    /**
     * 按属性查找对象列表, 默认匹配方式为Like.
     */
    public List<T> findByProperty(final String propertyName, final Object value) {
        Assert.hasText(propertyName, "propertyName不能为空");
        Criterion criterion = Restrictions.like(propertyName, (String) value,
                MatchMode.ANYWHERE);
        return find(criterion);
    }

    /**
     * 按属性查找唯一对象, 默认匹配方式为Like.
     */
    public T findUniqueByProperty(final String propertyName, final Object value) {
        Assert.hasText(propertyName, "propertyName不能为空");
        Criterion criterion = Restrictions.like(propertyName, (String) value,
                MatchMode.ANYWHERE);
        return (T) createCriteria(criterion).uniqueResult();
    }

    /**
     * 按HQL查询对象列表.
     * @param <T>
     * @param values
     * 数量可变的参数,按顺序绑定.
     */
    public <T> List<T> find(final String hql, final Object... values) {
        return createQuery(hql, values).list();
    }

    /**
     * 按HQL查询对象列表.
     * @param values
     * 命名参数,按名称绑定.
     */
    public <T> List<T> find(final String hql, final Map<String, ?> values) {
        return createQuery(hql, values).list();
    }

    /**
     * 按HQL查询唯一对象.
     * @param values
     * 数量可变的参数,按顺序绑定.
     */
    public <T> T findUnique(final String hql, final Object... values) {
        return (T) createQuery(hql, values).uniqueResult();
    }

    /**
     * 按HQL查询唯一对象.
     * @param values
     * 命名参数,按名称绑定.
     */
    public <T> T findUnique(final String hql, final Map<String, ?> values) {
        return (T) createQuery(hql, values).uniqueResult();
    }

    /**
     * 执行HQL进行批量修改/删除操作.
     * @param values
     * 数量可变的参数,按顺序绑定.
     * @return 更新记录数.
     */
    public int batchExecute(final String hql, final Object... values) {
        return createQuery(hql, values).executeUpdate();
    }

    /**
     * 执行HQL进行批量修改/删除操作.
     * @param values
     * 命名参数,按名称绑定.
     * @return 更新记录数.
     */
    public int batchExecute(final String hql, final Map<String, ?> values) {
        return createQuery(hql, values).executeUpdate();
    }

    /**
     * 根据查询HQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
     * @param values
     * 数量可变的参数,按顺序绑定.
     */
    public Query createQuery(final String queryString, final Object... values) {
        Assert.hasText(queryString, "queryString不能为空");
        Query query = getSession().createQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(String.valueOf(i), values[i]);
            }
        }
        return query;
    }

    /**
     * 根据查询HQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
     * @param values
     * 命名参数,按名称绑定.
     */
    public Query createQuery(final String queryString,
                             final Map<String, ?> values) {
        Query query = getSession().createQuery(queryString);
        if (values != null) {
            query.setProperties(values);
        }
        return query;
    }

    /**
     * 按Criteria查询对象列表.
     * @param criterions
     * 数量可变的Criterion.
     */
    public List<T> find(final Criterion... criterions) {
        return createCriteria(criterions).list();
    }

    /**
     * 按Criteria查询对象列表.
     * @param detachedCriteria
     * 数量可变的Criterion.
     */
    public List<T> find(final DetachedCriteria detachedCriteria) {
        return createCriteria(detachedCriteria).list();
    }

    /**
     * 按Criteria查询唯一对象.
     * @param criterions
     * 数量可变的Criterion.
     */
    public T findUnique(final Criterion... criterions) {
        return (T) createCriteria(criterions).uniqueResult();
    }

    /**
     * 按Criteria查询唯一对象.
     * @param detachedCriteria
     * 数量可变的Criterion.
     */
    public T findUnique(final DetachedCriteria detachedCriteria) {
        return (T) createCriteria(detachedCriteria).uniqueResult();
    }

    /**
     * 根据Criterion条件创建Criteria. 与find()函数可进行更加灵活的操作.
     * @param criterions
     * 数量可变的Criterion.
     */
    public Criteria createCriteria(final Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(clazz);
        for(Criterion criterion: criterions){
            criteria.add(criterion);
        }
        return criteria;
    }

    /**
     * 根据Criterion条件创建Criteria. 与find()函数可进行更加灵活的操作.
     * @param detachedCriteria
     * 数量可变的Criterion.
     */
    public Criteria createCriteria(final DetachedCriteria detachedCriteria) {
        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        return criteria;
    }

    /**
     * 初始化对象. 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化. 如果传入entity,
     * 则只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性. 如需初始化关联属性,需执行:
     * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
     * Hibernate.initialize
     * (user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
     */
    public void initProxyObject(Object proxy) {
        Hibernate.initialize(proxy);
    }

    /**
     * Flush当前Session.
     */
    public void flush() {
        getSession().flush();
    }

    /**
     * 为Query添加distinct transformer. 预加载关联对象的HQL会引起主对象重复, 需要进行distinct处理.
     */
    public Query distinct(Query query) {
        query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return query;
    }

    /**
     * 为Criteria添加distinct transformer. 预加载关联对象的HQL会引起主对象重复, 需要进行distinct处理.
     */
    public Criteria distinct(Criteria criteria) {
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria;
    }

    /**
     * 取得对象的主键名.
     */
    public String getIdName() {
        ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
        return meta.getIdentifierPropertyName();
    }

    /**
     * 判断对象的属性值在数据库内是否唯一.
     * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
     */
    public boolean isPropertyUnique(final String propertyName,
                                    final Object newValue, final Object oldValue) {
        if (newValue == null || newValue.equals(oldValue)) {
            return true;
        }
        Object object = findUniqueByProperty(propertyName, newValue);
        return (object == null);
    }
}
