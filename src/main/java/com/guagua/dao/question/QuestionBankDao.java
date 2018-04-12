package com.guagua.dao.question;

import com.guagua.bean.entity.question.Question;
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

    Integer countQuestionByType(int type);

    List<Question> findByType(int type);

    /**
     * 统计用户创建了多少题库
     *
     * @param userId user id
     * @return result
     */
    Integer countNUms(Integer userId);
}
