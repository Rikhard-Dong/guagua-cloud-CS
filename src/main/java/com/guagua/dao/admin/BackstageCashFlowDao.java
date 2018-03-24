package com.guagua.dao.admin;

import com.guagua.bean.entity.admin.BackstageCashFlow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-3-24 下午12:06
 * <p>
 * 后台资金流
 */
public interface BackstageCashFlowDao {


    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条记录
     *
     * @param flow
     * @return
     */
    Integer insertOne(@Param("flow") BackstageCashFlow flow);

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
     * 查询所有
     *
     * @return
     */
    List<BackstageCashFlow> findAll();

    /**
     * 根据用户id查询该用户在后台的流水记录
     *
     * @param objectId
     * @return
     */
    List<BackstageCashFlow> findByUserId(@Param("objectId") Integer objectId);
}
