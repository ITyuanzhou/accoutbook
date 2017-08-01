package com.zy.service;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * BaseService 定义Service的通用操作
 * Created by Administrator on 2017/6/23.
 */
public interface BaseService<T> {

    public Serializable save(final T entity);

    public void update(final T entity);

    public void saveOrUpdate(final T entity);

    public void delete(final T entity);

    public void delete(final Serializable id);

    public T get(final Serializable id);

    public List<T> get(final Collection<Serializable> ids);

    public List<T> getAll();

    public List<T> getAll(String orderByProperty, boolean isAsc);

    public List<T> findByProperty(final String propertyName, final Object value);

    public T findUniqueByProperty(final String propertyName, final Object value);

    public <T> List<T> find(final String hql, final Object... values);

    public <T> List<T> find(final String hql, final Map<String, ?> values);

    public <T> T findUnique(final String hql, final Object... values);

    public <T> T findUnique(final String hql, final Map<String, ?> values);

    public int batchExecute(final String hql, final Object... values);

    public int batchExecute(final String hql, final Map<String, ?> values);

    public Query createQuery(final String queryString, final Object... values);

    public Query createQuery(final String queryString,
                             final Map<String, ?> values);

    public List<T> find(final Criterion... criterions);

    public List<T> find(final DetachedCriteria detachedCriteria);

    public T findUnique(final Criterion... criterions);

    public T findUnique(final DetachedCriteria detachedCriteria);

    public Criteria createCriteria(final Criterion... criterions);

    public Criteria createCriteria(final DetachedCriteria detachedCriteria);

    public void initProxyObject(Object proxy);

    public void flush();

    public Query distinct(Query query);

    public Criteria distinct(Criteria criteria);

    public String getIdName();

    public boolean isPropertyUnique(final String propertyName,
                                    final Object newValue, final Object oldValue);

}
