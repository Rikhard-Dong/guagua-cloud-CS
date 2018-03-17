package com.guagua.dao.member;

import com.guagua.bean.entity.member.MemberAuthentication;

import java.util.List;

/**
 * @author ride
 * @date 18-3-16 下午4:40
 * <p>
 * 维护会员用户的实名认证表
 */
public interface MemberAuthenticationDao {

    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条认证记录
     *
     * @param authentication 认证内容
     * @return 插入结果
     */
    Integer insertOne(MemberAuthentication authentication);

    /* ************************************************
     * update
     *************************************************/

    Integer updateById(MemberAuthentication authentication);

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
    List<MemberAuthentication> findAll();

    /**
     * 查询所有未审核的信息
     *
     * @return 结果
     */
    List<MemberAuthentication> findAllUnaudited();

    /**
     * 查询所有审核通过的信息
     *
     * @return 结果
     */
    List<MemberAuthentication> findAllPass();

    /**
     * 审核所有未通过的信息
     *
     * @return 结果
     */
    List<MemberAuthentication> findAllFailPass();

    /**
     * 根据用户id查询申请信息
     *
     * @param userId 用户id
     * @return 结果
     */
    MemberAuthentication findByUserId(Integer userId);
}
