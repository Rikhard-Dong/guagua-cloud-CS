package com.guagua.dao.admin;

import com.guagua.bean.entity.admin.HandleEnterpriseAuthentication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-3-17 下午2:12
 * <p>
 * 企业认证信息结果信息结果记录
 */
public interface HandleEnterpriseAuthenticationDao {

    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条处理记录
     *
     * @param authentication 处理记录
     * @return  result
     */
    Integer insertOne(HandleEnterpriseAuthentication authentication);

    /* ************************************************
     * select
     *************************************************/

    /**
     * 查询所有处理结果
     *
     * @return result
     */
    List<HandleEnterpriseAuthentication> findAll();

    /**
     * 查询单个申请的认证记录, 可能会有多次多条, 多次申请都不通过的情况
     *
     * @return result
     */
    List<HandleEnterpriseAuthentication> findByApplyId(@Param("applyId") Integer applyId);

    /**
     * 结果应该是同上, 因为一个用户只对应一次认证
     *
     * @return result
     */
    List<HandleEnterpriseAuthentication> findByApplyUserId(@Param("applyUserId") Integer applyUserId);

    /**
     * 根据处理者返回
     *
     * @return result
     */
    List<HandleEnterpriseAuthentication> findByHandleUserId(@Param("handleUserId") Integer handleUserId);
}
