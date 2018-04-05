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

    Integer countByQuestionId(Integer questionId);

    QuestionItem findById(Integer id);

    List<QuestionItem> findByQuestionId(Integer questionId);
}
