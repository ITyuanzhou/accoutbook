package com.zy.dao.impl;

import com.zy.dao.AttachDao;
import com.zy.model.TbAttachEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ZJZL_HP on 2017/7/25.
 */
@Repository("/attachDao")
public class AttachDaoImpl extends BaseDaoImpl<TbAttachEntity> implements AttachDao {
}
