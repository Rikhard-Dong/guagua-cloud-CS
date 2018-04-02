package com.guagua.dao.common;

import com.guagua.bean.entity.common.TaskEmployment;
import com.guagua.bean.entity.common.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-3-25 上午10:24
 * <p>
 * 任务雇佣关系
 */
public interface TaskEmploymentDao {

    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条雇佣关系
     *
     * @param employment
     * @return
     */
    Integer insertOne(TaskEmployment employment);

    /* ************************************************
     * update
     *************************************************/

    /**
     * 更新雇佣状态
     *
     * @param id
     * @param status
     * @return
     */
    Integer updateStatus(@Param("id") Integer id,
                         @Param("status") Integer status);


    /**
     * 更新评分
     *
     * @param id
     * @param score
     * @return
     */
    Integer updateScore(@Param("id") Integer id,
                        @Param("score") Integer score);

    /* ************************************************
     * delete
     *************************************************/



    /* ************************************************
     * select
     *************************************************/

    /**
     * 统计该任务参与的人数
     *
     * @param taskId
     * @return
     */
    Integer countNumber(@Param("taskId") Integer taskId);

    /**
     * 查询该任务下所有的雇佣关系
     *
     * @param taskId
     * @return
     */
    List<TaskEmployment> findByTaskId(@Param("taskId") Integer taskId);

    /**
     * 查询该任务下的申请任务的人
     *
     * @param taskId
     * @return
     */
    List<User> findCustomerServiceByTaskId(@Param("taskId") Integer taskId);

    /**
     * 根据任务id和会员id查询单个申请
     *
     * @param taskId
     * @param memberId
     * @return
     */
    TaskEmployment findByTaskIdAndMemberId(@Param("taskId") Integer taskId,
                                           @Param("memberId") Integer memberId);

    /**
     * 根据客服的id查询她当前的任务
     *
     * @param memberId
     * @return
     */
    List<Integer> getTaskIdsByMemberId(Integer memberId);

    /**
     * 查询该企业建立联系的所有客服
     *
     * @param userId
     * @return
     */
    List<User> findAllCustomerServiceByEnterpriseId(Integer userId);
}
