package com.guagua.dao.member;

import com.guagua.bean.entity.member.SatisfactionEvaluation;
import org.apache.ibatis.annotations.Param;

/**
 * @author ride
 * @date 18-4-11 下午3:13
 */
public interface SatisfactionEvaluationDao {

    /* ************************************************
     * insert
     *************************************************/

    /**
     * insert one record
     *
     * @param evaluation evaluation
     * @return result
     */
    Integer insertOne(SatisfactionEvaluation evaluation);

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
     * 统计该客服在该任务下的评价人数
     *
     * @param taskId   task id
     * @param memberId member id
     * @return result
     */
    Integer countEvaluateNum(@Param("taskId") Integer taskId,
                             @Param("memberId") Integer memberId);


    /**
     * 统计该客服在该任务下的评分总和
     *
     * @param taskId   task id
     * @param memberId member id
     * @return result
     */
    Integer sumOfScore(@Param("taskId") Integer taskId,
                       @Param("memberId") Integer memberId);
}
