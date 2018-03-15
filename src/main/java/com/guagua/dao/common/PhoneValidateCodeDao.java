package com.guagua.dao.common;

import com.guagua.bean.entity.common.PhoneValidateCode;

import java.util.List;

/**
 * @author ride
 * @date 18-3-12 下午6:34
 */
public interface PhoneValidateCodeDao {

    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条手机与验证码
     *
     * @param code 带插入对象
     * @return 结果
     */
    Integer insertPhoneValidateCode(PhoneValidateCode code);

    /* ************************************************
     * update
     *************************************************/

    /* ************************************************
     * delete
     *************************************************/

    /**
     * 根据id删除对应的数据
     *
     * @param id id
     * @return 执行成功与否
     */
    Integer deleteById(Integer id);

    /* ************************************************
     * select
     *************************************************/

    /**
     * 展示所有的记录
     *
     * @return 结果集
     */
    List<PhoneValidateCode> findAll();

    /**
     * 根据phone查询最近的手机验证码信息
     *
     * @param phone phone
     * @return 查询到返回结果, 查询不到返回null
     */
    PhoneValidateCode findLatestPhoneValidateCodeByPhone(String phone);
}
