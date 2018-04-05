package com.guagua.dao.question;

import com.guagua.bean.entity.question.Question;
import com.guagua.bean.entity.question.QuestionExaminationPaper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-4-4 下午10:37
 */
public interface QuestionExaminationPaperDao {
    /* ************************************************
     * insert
     *************************************************/

    Integer insertOne(QuestionExaminationPaper questionExaminationPaper);

    /* ************************************************
     * update
     *************************************************/

    /* ************************************************
     * delete
     *************************************************/

    /* ************************************************
     * select
     *************************************************/

    /**
     * 统计该试卷该类型的题目拥有多少题了
     *
     * @param examinationId
     * @param type
     * @return
     */
    Integer countByQuestionTypeAndExamination(@Param("examinationId") Integer examinationId,
                                              @Param("type") Integer type);

    List<Question> findByQuestionTypeAndExamination(@Param("examinationId") Integer examinationId,
                                                    @Param("type") Integer type);
}
