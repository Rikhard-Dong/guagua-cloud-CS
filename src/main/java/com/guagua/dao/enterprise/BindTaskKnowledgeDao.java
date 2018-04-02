package com.guagua.dao.enterprise;

import com.guagua.bean.entity.common.KnowledgeBase;
import com.guagua.bean.entity.enterprise.BindTaskKnowledge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-3-29 下午7:18
 * <p>
 * 将任务和知识库相绑定
 */
public interface BindTaskKnowledgeDao {


    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一套绑定资料
     *
     * @param bindTaskKnowledge
     * @return
     */
    Integer insertOne(BindTaskKnowledge bindTaskKnowledge);


    /**
     * 批量插入数据
     *
     * @param bindTaskKnowledgeList
     * @return
     */
    Integer insertBatch(List<BindTaskKnowledge> bindTaskKnowledgeList);


    /* ************************************************
     * update
     *************************************************/


    /* ************************************************
     * delete
     *************************************************/

    /**
     * 删除联系
     *
     * @param id
     * @return
     */
    Integer deleteOne(Integer id);

    /* ************************************************
     * select
     *************************************************/

    /**
     * 查询所有任务和知识库的联系
     *
     * @param taskId
     * @return
     */
    List<BindTaskKnowledge> findByTaskId(Integer taskId);

    /**
     * 根据任务id查询任务下的所有知识库
     *
     * @param taskId
     * @return
     */
    List<KnowledgeBase> findBaseByTaskId(Integer taskId);

    /**
     * 根据任务id和知识库id查询
     *
     * @param taskId
     * @param knowledgeId
     * @return
     */
    BindTaskKnowledge findByTaskIdAndKnowledge(@Param("taskId") Integer taskId,
                                               @Param("knowledgeId") Integer knowledgeId);
}
