package com.guagua.dao.common;

import com.guagua.bean.entity.common.TaskApplication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-3-24 下午9:15
 * <p>
 * 任务申请记录表
 */
public interface TaskApplicationDao {

    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条申请记录
     *
     * @param application application
     * @return result
     */
    Integer insertOne(TaskApplication application);

    /* ************************************************
     * update
     *************************************************/

    /**
     * 更新任务状态
     *
     * @param id     id
     * @param status status
     * @return result
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
     * 查询用户是否参与过本次任务的投标
     *
     * @return result
     */
    TaskApplication findByTaskIdAndMemberId(@Param("taskId") Integer taskId,
                                            @Param("memberId") Integer memberId);

    /**
     * 查询该任务下的所有申请
     *
     * @param taskId task id
     * @return result
     */
    List<TaskApplication> findByTaskId(Integer taskId);

    /**
     * 查询单条
     *
     * @param applicationId application id
     * @return result
     */
    TaskApplication findById(Integer applicationId);

    /**
     * 查询该任务下所有未处理的申请
     *
     * @param taskId task id
     * @return result
     */
    List<TaskApplication> findUntreatedByTaskId(Integer taskId);

    /**
     * 根据任务id和任务状态查询
     *
     * @param taskId task id
     * @param status status
     * @return result
     */
    List<TaskApplication> findByTaskIdAndStatus(@Param("taskId") Integer taskId,
                                                @Param("status") Integer status);

    /**
     * 根据申请的状态查询申请数量
     *
     * @param memberId member id
     * @param status   status
     * @return result
     */
    Integer countTaskByStatus(@Param("memberId") Integer memberId,
                              @Param("status") Integer status);
}
