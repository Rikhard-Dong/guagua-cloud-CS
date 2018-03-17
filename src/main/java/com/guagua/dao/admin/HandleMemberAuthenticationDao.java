package com.guagua.dao.admin;

import com.guagua.bean.entity.admin.HandleMemberAuthentication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-3-16 下午9:49
 * <p>
 * 会员用户认证申请处理结果
 */
public interface HandleMemberAuthenticationDao {

    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条处理结果
     *
     * @param authentication 插入数据
     * @return 结果
     */
    Integer insertOne(HandleMemberAuthentication authentication);

    /* ************************************************
     * update
     *************************************************/

    Integer updateOne(HandleMemberAuthentication authentication);

    /* ************************************************
     * delete
     *************************************************/

    /* ************************************************
     * select
     *************************************************/

    /**
     * 查询所有处理结果
     *
     * @return result
     */
    List<HandleMemberAuthentication> findAll();

    /**
     * 查询单个申请的认证记录, 可能会有多次多条, 多次申请都不通过的情况
     *
     * @return result
     */
    List<HandleMemberAuthentication> findByApplyId(@Param("applyId") Integer applyId);

    /**
     * 结果应该是同上, 因为一个用户只对应一次认证
     *
     * @return result
     */
    List<HandleMemberAuthentication> findByApplyUserId(@Param("applyUserId") Integer applyUserId);

    /**
     * 根据处理者返回
     *
     * @return result
     */
    List<HandleMemberAuthentication> findByHandleUserId(@Param("handleUserId") Integer handleUserId);
}
