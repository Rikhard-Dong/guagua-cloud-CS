package com.guagua.dao.question;

import com.guagua.bean.entity.question.QuestionItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-4-4 下午1:00
 */
public interface QuestionItemDao {
    /* ************************************************
     * insert
     *************************************************/

    Integer insertOne(@Param("item") QuestionItem item);

    /* ************************************************
     * update
     *************************************************/


    /* ************************************************
     * delete
     *************************************************/

    Integer deleteOne(Integer itemId);

    /* ************************************************
     * select
     *************************************************/

    /**
     * 统计问题下有多个选项
     *
     * @param questionId
     * @return
     */
    Integer countByQuestionId(Integer questionId);

    /**
     * 查询某个具体的选项
     *
     * @param id
     * @return
     */
    QuestionItem findById(Integer id);

    /**
     * 查询该问题下所有的选项
     *
     * @param questionId
     * @return
     */
    List<QuestionItem> findByQuestionId(Integer questionId);
}
