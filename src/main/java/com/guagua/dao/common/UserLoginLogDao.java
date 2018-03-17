package com.guagua.dao.common;

import com.guagua.bean.entity.common.UserLoginLog;

import java.util.List;

/**
 * @author ride
 * @date 18-3-13 下午9:27
 * <p>
 * 用户登录日志 dao
 */
public interface UserLoginLogDao {

    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条登录记录
     *
     * @param userLoginLog 登录信息
     * @return 结果
     */
    Integer insertUserLoginLog(UserLoginLog userLoginLog);

    /* ************************************************
     * select
     *************************************************/

    List<UserLoginLog> findAll();
}
