package com.zy.service.impl;

import com.zy.model.TbDetailAaBillingEntity;
import com.zy.model.TbUserEntity;
import com.zy.service.DetailAaBillingService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
@Service("datailAaBillingService")
public class DetailAaBillingServiceImpl extends BaseServiceImpl<TbDetailAaBillingEntity>
        implements DetailAaBillingService {

    /**
     * 得到用户的最新账户信息
     *
     * @param user
     * @param time
     * @return
     */
    public List<TbDetailAaBillingEntity> GetDetailAaBillingList(TbUserEntity user, Timestamp time) {
        String hql = "from TbDetailAaBillingEntity as d where d.detailAaBilUser.userId = :userId and d.detailAaBilBilling.aaBilTime > :time order by d.detailAaBilBilling.aaBilTime desc";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", user.getUserId());
        map.put("time", time);
        return find(hql, map);
    }
}
