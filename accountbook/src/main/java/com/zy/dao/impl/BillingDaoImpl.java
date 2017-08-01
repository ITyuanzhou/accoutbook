package com.zy.dao.impl;

import com.zy.dao.BillingDao;
import com.zy.model.TbBillingEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/6/23.
 */
@Repository("billingDao")
public class BillingDaoImpl extends BaseDaoImpl<TbBillingEntity> implements BillingDao {
}
