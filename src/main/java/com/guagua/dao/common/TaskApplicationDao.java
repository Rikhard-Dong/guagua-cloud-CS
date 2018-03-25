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
     * @param application
     * @return
     */
    Integer insertOne(TaskApplication application);

    /* ************************************************
     * update
     *************************************************/

    /**
     * 更新任务状态
     *
     * @param id
     * @param status
     * @return
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
     * @return
     */
    TaskApplication findByTaskIdAndMemberId(@Param("taskId") Integer taskId,
                                            @Param("memberId") Integer memberId);

    /**
     * 查询该任务下的所有申请
     *
     * @param taskId
     * @return
     */
    List<TaskApplication> findByTaskId(Integer taskId);

    /**
     * 查询单条
     *
     * @param applicationId
     * @return
     */
    TaskApplication findById(Integer applicationId);

    /**
     * 查询该任务下所有未处理的申请
     *
     * @param taskId
     * @return
     */
    List<TaskApplication> findUntreatedByTaskId(Integer taskId);

    /**
     * 根据任务id和任务状态查询
     *
     * @param taskId
     * @param status
     * @return
     */
    List<TaskApplication> findByTaskIdAndStatus(@Param("taskId") Integer taskId,
                                                @Param("status") Integer status);
}
