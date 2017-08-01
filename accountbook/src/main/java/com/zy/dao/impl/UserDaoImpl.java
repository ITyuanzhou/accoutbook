package com.zy.dao.impl;

import com.zy.dao.UserDao;
import com.zy.model.TbUserEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/6/23.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<TbUserEntity> implements UserDao {

}
