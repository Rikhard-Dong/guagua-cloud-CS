package com.guagua.dao.question;

import com.guagua.bean.entity.question.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-4-4 下午1:00
 */
public interface QuestionDao {
    /* ************************************************
     * insert
     *************************************************/

    Integer insertOne(@Param("question") Question question);

    /* ************************************************
     * update
     *************************************************/

    Integer updateOne(@Param("question") Question question);

    Integer updateStandardAnswer(@Param("questionId") Integer var1,
                                 @Param("hasStandardAnswer") Integer var2,
                                 @Param("standardAnswer") String var3);

    Integer updateStatus(@Param("questionId") Integer questionId,
                         @Param("status") Integer status);

    /* ************************************************
     * delete
     *************************************************/

    Integer deleteOne(Integer id);

    /* ************************************************
     * select
     *************************************************/

    List<Question> findAllByBankId(Integer bankId);

    List<Question> findSingleChoiceByBankId(Integer bankId);

    List<Question> findMultipleChoiceByBankId(Integer bankId);

    List<Question> findJudgmentByBankId(Integer bankeId);

    List<Question> findTextByBankId(Integer bankId);

    Question findById(Integer questionId);

    List<Question> findAllByBankIdAndType(@Param("bankId") Integer bankId,
                                          @Param("type") Integer type);
}
