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
     * @param item item
     * @return result
     */
    Integer insertOne(@Param("item") KnowledgeBaseItem item);

    /* ************************************************
     * update
     *************************************************/

    /**
     * 更新一个知识库条目
     *
     * @param item item
     * @return result
     */
    Integer updateOne(@Param("item") KnowledgeBaseItem item);

    /* ************************************************
     * delete
     *************************************************/

    /**
     * 删除一个item条目
     *
     * @param id id
     * @return result
     */
    Integer deleteOne(Integer id);

    /* ************************************************
     * select
     *************************************************/

    /**
     * 查询一个知识库下所有的条目
     *
     * @param knowledgeBaseId knowledge base id
     * @return list of knowledge base item
     */
    List<KnowledgeBaseItem> findByKnowledgeBase(Integer knowledgeBaseId);

    /**
     * 查找一条item
     *
     * @param itemId item id
     * @return knowledge base
     */
    KnowledgeBaseItem findByItemId(Integer itemId);

    /**
     * 对知识库进行搜索
     *
     * @param baseIds 相关知识库id列表
     * @param keyword 关键字
     * @return list of knowledge base item
     */
    List<KnowledgeBaseItem> searchKeyWord(@Param("bases") List<Integer> baseIds,
                                          @Param("keyword") String keyword);
}
