package com.guagua.dao.member;

import com.guagua.bean.entity.member.MemberCashFlow;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author ride
 * @date 18-3-19 下午7:27
 * <p>
 * 资金流明细
 */
public interface MemberCashFlowDao {

    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条资金流记录
     *
     * @param flow
     * @return
     */
    Integer insertOne(MemberCashFlow flow);

    /* ************************************************
     * update
     *************************************************/

    /* ************************************************
     * delete
     *************************************************/

    /* ************************************************
     * select
     *************************************************/

    /**
     * 针对某个查询所有资金流信息
     *
     * @param propertyId
     * @return
     */
    List<MemberCashFlow> findByPropertyId(int propertyId);

    /**
     * 根据时间查询资金流
     *
     * @param id
     * @param startTime
     * @param endTime
     * @return
     */
    List<MemberCashFlow> findByPropertyIdAndDate(@Param("propertyId") Integer id,
                                                 @Param("startTime") Date startTime,
                                                 @Param("endTime") Date endTime);
}
