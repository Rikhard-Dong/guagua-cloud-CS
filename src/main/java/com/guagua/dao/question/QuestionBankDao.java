package com.guagua.dao.question;

import com.guagua.bean.entity.question.QuestionBank;

import java.util.List;

/**
 * @author ride
 * @date 18-4-4 上午10:46
 */
public interface QuestionBankDao {

    /* ************************************************
     * insert
     *************************************************/

    Integer insertOne(QuestionBank bank);

    /* ************************************************
     * update
     *************************************************/

    Integer updateOne(QuestionBank bank);

    /* ************************************************
     * delete
     *************************************************/

    Integer deleteOne(Integer id);

    /* ************************************************
     * select
     *************************************************/

    List<QuestionBank> queryByCreator(Integer creator);

    QuestionBank findById(Integer id);

}
