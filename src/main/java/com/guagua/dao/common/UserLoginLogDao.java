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

    /**
     * user login log
     *
     * @return list of all user login logs
     */
    List<UserLoginLog> findAll();

    /**
     * list user login log by user id
     *
     * @param userId user id
     * @return list of user login log
     */
    List<UserLoginLog> findByUserId(Integer userId);
}
