package com.guagua.dao.enterprise;

import com.guagua.bean.entity.enterprise.EnterpriseCashFlow;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author ride
 * @date 18-3-19 下午10:08
 */
public interface EnterpriseCashFlowDao {
    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条资金流记录
     *
     * @param flow
     * @return
     */
    Integer insertOne(EnterpriseCashFlow flow);

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
     * 查询个人的流水账信息
     *
     * @param propertyId
     * @return
     */
    List<EnterpriseCashFlow> findByPropertyId(Integer propertyId);

    /**
     * 查询指定时间内的资金流水情况
     *
     * @param id
     * @param startDate
     * @param endDate
     * @return
     */
    List<EnterpriseCashFlow> findByPropertyIdAndDate(@Param("propertyId") int id,
                                                     @Param("startDate") Date startDate,
                                                     @Param("endDate") Date endDate);
}
