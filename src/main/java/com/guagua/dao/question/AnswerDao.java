package com.guagua.dao.question;

import com.guagua.bean.entity.question.Answer;

import java.util.List;

/**
 * @author ride
 * @date 18-4-6 下午1:08
 */
public interface AnswerDao {

    /* ************************************************
     * insert
     *************************************************/

    Integer insertOne(Answer answer);

    /**
     * 批量插入
     *
     * @param temp
     * @return
     */
    Integer insertBatch(List<Answer> temp);


    /* ************************************************
     * update
     *************************************************/


    /* ************************************************
     * delete
     *************************************************/

    /* ************************************************
     * select
     *************************************************/

    List<Answer> findBySheetId(Integer sheetId);
}
