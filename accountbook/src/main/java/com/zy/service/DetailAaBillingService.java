package com.zy.service;


import com.zy.model.TbDetailAaBillingEntity;
import com.zy.model.TbUserEntity;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2017/7/2.
 */
public interface DetailAaBillingService extends BaseService<TbDetailAaBillingEntity>{

    public List<TbDetailAaBillingEntity> GetDetailAaBillingList(TbUserEntity user,Timestamp time);
}
