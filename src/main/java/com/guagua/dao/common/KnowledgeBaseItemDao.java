package com.guagua.dao.common;

import com.guagua.bean.entity.common.KnowledgeBase;
import com.guagua.bean.entity.common.KnowledgeBaseItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-3-18 下午9:11
 * <p>
 * 对知识库item条目进行增删查改
 */
public interface KnowledgeBaseItemDao {


    /* ************************************************
     * insert
     *************************************************/

    /**
     * 增加一个知识库条目
     *
     * @param item
     * @return
     */
    Integer insertOne(@Param("item") KnowledgeBaseItem item);

    /* ************************************************
     * update
     *************************************************/

    /**
     * 更新一个知识库条目
     *
     * @param item
     * @return
     */
    Integer updateOne(@Param("item") KnowledgeBaseItem item);

    /* ************************************************
     * delete
     *************************************************/

    /**
     * 删除一个item条目
     *
     * @param id
     * @return
     */
    Integer deleteOne(Integer id);

    /* ************************************************
     * select
     *************************************************/

    /**
     * 查询一个知识库下所有的条目
     *
     * @param knowledgeBaseId
     * @return
     */
    List<KnowledgeBaseItem> findByKnowledgeBase(Integer knowledgeBaseId);

    /**
     * 查找一条item
     *
     * @param itemId
     * @return
     */
    KnowledgeBaseItem findByItemId(Integer itemId);
}
