package com.guagua.dao.common;

import com.guagua.bean.entity.common.UserLoginLog;

/**
 * @author ride
 * @date 18-3-13 下午9:27
 * <p>
 * 用户登录日志 dao
 */
public interface UserLoginLogDao {

    Integer insertUserLoginLog(UserLoginLog userLoginLog);
}
