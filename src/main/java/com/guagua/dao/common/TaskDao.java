package com.guagua.dao.common;

import com.guagua.bean.entity.common.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-3-23 下午4:26
 * <p>
 * 任务操作
 */
public interface TaskDao {


    /* ************************************************
     * insert
     *************************************************/


    /**
     * 创建一个任务
     *
     * @param task 任务
     * @return 结果
     */
    Integer insertOne(Task task);


    /* ************************************************
     * update
     *************************************************/

    /**
     * 更新状态
     *
     * @param id     id
     * @param status 状态
     * @return 结果
     */
    Integer updateStatus(@Param("id") Integer id,
                         @Param("status") Integer status);


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
    List<Task> findAll();

    /**
     * 查询某种状态的所有任务
     *
     * @param status
     * @return
     */
    List<Task> findByStatus(@Param("status") Integer status);

    /**
     * 企业查询该企业发布的任务
     *
     * @param enterpriseId 企业id
     * @return
     */
    List<Task> findByEnterpriseId(@Param("enterpriseId") Integer enterpriseId);

    /**
     * 企业查询该企业发布的制定状态的任务任务
     *
     * @param enterpriseId 企业id
     * @param status       状态
     * @return 结果
     */
    List<Task> findByEnterpriseIdAndStatus(@Param("enterpriseId") Integer enterpriseId,
                                           @Param("status") Integer status);

    /**
     * 查询详细信息
     *
     * @param id
     * @return
     */
    Task findByTaskId(Integer id);
}
