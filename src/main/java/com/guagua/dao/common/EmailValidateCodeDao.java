package com.guagua.dao.common;

import com.guagua.bean.entity.common.EmailValidateCode;

import java.util.List;

/**
 * @author ride
 * @date 18-3-15 下午3:07
 * <p>
 * 维护验证码
 */
public interface EmailValidateCodeDao {

    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条验证码
     *
     * @param code 带插入对象
     * @return 结果
     */
    Integer insertEmailValidateCode(EmailValidateCode code);

    /* ************************************************
     * delete
     *************************************************/

    /**
     * 根据id删除一条邮箱验证码
     *
     * @param id id
     * @return 操作结果
     */
    Integer deleteEmailValidateCodeById(Integer id);

    /* ************************************************
     * select
     *************************************************/

    /**
     * 根据邮箱查询最近的验证码
     *
     * @param email 邮箱
     * @return 结果
     */
    EmailValidateCode findLatestEmailValidateCodeByEmail(String email);

    /**
     * 返回所有结果
     *
     * @return 结束
     */
    List<EmailValidateCode> findAll();
}
