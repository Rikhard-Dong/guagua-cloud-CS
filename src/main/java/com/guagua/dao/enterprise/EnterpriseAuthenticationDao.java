package com.guagua.dao.enterprise;

import com.guagua.bean.entity.enterprise.EnterpriseAuthentication;

import java.util.List;

/**
 * @author ride
 * @date 18-3-17 下午2:13
 * <p>
 * 企业认证
 */
public interface EnterpriseAuthenticationDao {
    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条企业认证信息
     *
     * @param authentication 企业认证信息
     * @return result
     */
    Integer insertOne(EnterpriseAuthentication authentication);


    /* ************************************************
     * update
     *************************************************/

    /**
     * 重新更新企业认证信息
     *
     * @param authentication 企业认证信息
     * @return result
     */
    Integer updateByUserId(EnterpriseAuthentication authentication);


    /* ************************************************
     * delete
     *************************************************/

    /* ************************************************
     * select
     *************************************************/

    /**
     * 查询所有的审核信息
     *
     * @return 结果
     */
    List<EnterpriseAuthentication> findAll();

    /**
     * 查询所有未审核的信息
     *
     * @return 结果
     */
    List<EnterpriseAuthentication> findAllUnaudited();

    /**
     * 查询所有审核通过的信息
     *
     * @return 结果
     */
    List<EnterpriseAuthentication> findAllPass();

    /**
     * 审核所有未通过的信息
     *
     * @return 结果
     */
    List<EnterpriseAuthentication> findAllFailPass();

    /**
     * 根据用户id查询申请信息
     *
     * @param userId 用户id
     * @return 结果
     */
    EnterpriseAuthentication findByUserId(Integer userId);
}