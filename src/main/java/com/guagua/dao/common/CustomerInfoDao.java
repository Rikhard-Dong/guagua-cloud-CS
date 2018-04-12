package com.guagua.dao.common;

import com.guagua.bean.entity.common.CustomerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-4-1 下午7:20
 */
public interface CustomerInfoDao {
    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条
     *
     * @param customer customer
     * @return  result
     */
    Integer insertOne(CustomerInfo customer);

    /* ************************************************
     * update
     *************************************************/

    /**
     * 更新一条
     *
     * @param customer
     * @return
     */
    Integer updateOne(CustomerInfo customer);

    /* ************************************************
     * delete
     *************************************************/

    /**
     * 删除一套
     *
     * @param uuid
     * @return
     */
    Integer deleteOne(String uuid);

    /* ************************************************
     * select
     *************************************************/

    /**
     * 根据任务id查询
     *
     * @param taskId
     * @return
     */
    List<CustomerInfo> findByTaskId(Integer taskId);

    /**
     * 根据处理者查询
     *
     * @param processorId
     * @return
     */
    List<CustomerInfo> findByProcessorId(Integer processorId);

    /**
     * 查询某个客服对应某个任务处理的客户信息
     *
     * @param taskId
     * @param processorId
     * @return
     */
    List<CustomerInfo> findByTaskIdAndProcessorId(@Param("taskId") Integer taskId,
                                                  @Param("processorId") Integer processorId);

}
