package com.guagua.dao.common;

import com.guagua.bean.entity.common.KnowledgeBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-3-18 下午6:56
 * <p>
 * 知识库数据库操作
 */
public interface KnowledgeBaseDao {

    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一个知识库
     *
     * @param knowledgeBase 知识库
     * @return result
     */
    Integer insertOne(@Param("knowledgeBase") KnowledgeBase knowledgeBase);


    /* ************************************************
     * update
     *************************************************/

    /**
     * 更新知识库信息
     *
     * @param base base
     * @return result
     */
    Integer updateOne(@Param("base") KnowledgeBase base);

    /* ************************************************
     * delete
     *************************************************/

    /**
     * 根据id删除知识库
     *
     * @param id 知识库id
     * @return result
     */
    Integer deleteById(Integer id);

    /* ************************************************
     * select
     *************************************************/

    /**
     * 查找该创建者所有的知识库
     *
     * @param creatorId 创建者id
     * @return
     */
    List<KnowledgeBase> findByCreatorId(Integer creatorId);

    /**
     * 根据id查找知识库
     *
     * @param id id
     * @return result
     */
    KnowledgeBase findById(Integer id);
}
