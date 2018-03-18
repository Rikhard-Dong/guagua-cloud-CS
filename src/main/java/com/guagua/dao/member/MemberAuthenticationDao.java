package com.guagua.dao.member;

import com.guagua.bean.entity.member.MemberAuthentication;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 更新认证内容
     *
     * @param authentication 认证内容
     * @return result
     */
    Integer updateById(MemberAuthentication authentication);

    /**
     * 更新个人认证的状态
     *
     * @param applyId id
     * @param status  认证状态
     * @return result
     */
    Integer updateStatusById(@Param("id") Integer applyId,
                             @Param("status") Integer status);

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

    /**
     * 根据id查询审核记录
     *
     * @param applyId id
     * @return result
     */
    MemberAuthentication findById(Integer applyId);


}
