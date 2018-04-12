package com.guagua.dao.common;

import com.guagua.bean.entity.common.Task;
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
     * @param employment employment
     * @return result
     */
    Integer insertOne(TaskEmployment employment);

    /* ************************************************
     * update
     *************************************************/

    /**
     * 更新雇佣状态
     *
     * @param id     id
     * @param status status
     * @return result
     */
    Integer updateStatus(@Param("id") Integer id,
                         @Param("status") Integer status);


    /**
     * 更新评分
     *
     * @param id    id
     * @param score score
     * @return result
     */
    Integer updateScore(@Param("id") Integer id,
                        @Param("score") Double score);

    /* ************************************************
     * delete
     *************************************************/



    /* ************************************************
     * select
     *************************************************/

    /**
     * 统计该任务参与的人数
     *
     * @param taskId task id
     * @return result
     */
    Integer countNumber(@Param("taskId") Integer taskId);

    /**
     * 查询该任务下所有的雇佣关系
     *
     * @param taskId task id
     * @return result
     */
    List<TaskEmployment> findByTaskId(@Param("taskId") Integer taskId);

    /**
     * 查询该任务下的申请任务的人
     *
     * @param taskId task id
     * @return result
     */
    List<User> findCustomerServiceByTaskId(@Param("taskId") Integer taskId);

    /**
     * 根据任务id和会员id查询单个申请
     *
     * @param taskId   task id
     * @param memberId member id
     * @return result
     */
    TaskEmployment findByTaskIdAndMemberId(@Param("taskId") Integer taskId,
                                           @Param("memberId") Integer memberId);

    /**
     * 根据客服的id查询她当前的任务
     *
     * @param memberId member id
     * @return result
     */
    List<Integer> getTaskIdsByMemberId(Integer memberId);

    /**
     * 查询该企业建立联系的所有客服
     *
     * @param userId user id
     * @return result
     */
    List<User> findAllCustomerServiceByEnterpriseId(Integer userId);

    /**
     * 根据memberId查询该会员参与的所有任务
     *
     * @param memberId member id
     * @return result
     */
    List<Task> findParticipateAllByUserId(Integer memberId);

    List<Task> findParticipateNotStartByUserId(Integer userId);

    List<Task> findParticipateHaveInHandByUserId(Integer userId);

    List<Task> findParticipateEndByUserId(Integer userId);

    List<Task> findParticipateCancelByUserId(Integer userId);

    /**
     * 查询用户的任务数据统计
     *
     * @param memberId member id
     * @param status   status
     * @return result
     */
    Integer countNumByMemberAndStatus(@Param("memberId") Integer memberId,
                                      @Param("status") Integer status);
}
