package com.guagua.dao.common;

import com.guagua.bean.entity.common.Task;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
     * @return list of all task
     */
    List<Task> findAll();

    /**
     * 查询某种状态的所有任务
     *
     * @param status status
     * @return list of task
     */
    List<Task> findByStatus(@Param("status") Integer status);

    /**
     * 企业查询该企业发布的任务
     *
     * @param enterpriseId 企业id
     * @return list of task
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
     * 会员用户查询所有状态为1的任务
     *
     * @return list of members
     */
    List<Task> findAllByMember();

    /**
     * 查询详细信息
     *
     * @param id id
     * @return task
     */
    Task findByTaskId(Integer id);

    /**
     * @param startTime start time
     * @param endTime   end time
     * @return num of task
     */
    Integer countNum(@Param("startTime") Date startTime,
                     @Param("endTime") Date endTime);


    /**
     * @param status    status
     * @param startTime start time
     * @param endTime   end time
     * @return num of task by type
     */
    Integer countNumByStatus(@Param("status") Integer status,
                             @Param("startTime") Date startTime,
                             @Param("endTime") Date endTime);

    /**
     * 统计企业发布的任务数量
     *
     * @param userId user id
     * @return result
     */
    Integer countNumByUserId(@Param("userId") Integer userId);

    /**
     * 根据任务状态统计企业发布的不同状态的任务
     *
     * @param userId user id
     * @param status status
     * @return result
     */
    Integer countNumByStatusAndUserId(@Param("userId") Integer userId,
                                      @Param("status") Integer status);
}
