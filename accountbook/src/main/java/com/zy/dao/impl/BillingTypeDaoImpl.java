package com.zy.dao.impl;

import com.zy.dao.BillingTypeDao;
import com.zy.model.TbBillingTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/6/23.
 */
@Repository("billingTypeDao")
public class BillingTypeDaoImpl extends BaseDaoImpl<TbBillingTypeEntity> implements BillingTypeDao{
}
