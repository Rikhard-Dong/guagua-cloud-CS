package com.guagua.dao.question;

import com.guagua.bean.entity.question.Answer;
import com.guagua.bean.entity.question.AnswerSheet;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author ride
 * @date 18-4-6 下午1:08
 */
public interface AnswerSheetDao {
    /* ************************************************
     * insert
     *************************************************/

    Integer insertOne(AnswerSheet sheet);

    /* ************************************************
     * update
     *************************************************/

    Integer updateScore(@Param("id") Integer sheetId,
                        @Param("score") Integer score,
                        @Param("remark") String remark,
                        @Param("markTime") Date curDate,
                        @Param("evaluationStatus") Integer status);

    /* ************************************************
     * delete
     *************************************************/

    Integer deleteOne(Integer id);

    /* ************************************************
     * select
     *************************************************/

    List<AnswerSheet> findByPaperId(Integer paperId);

    List<AnswerSheet> findByTaskId(Integer taskId);

    AnswerSheet findById(Integer id);

    AnswerSheet findByAnswererAndTaskId(@Param("answerer") Integer answerer,
                                        @Param("taskId") Integer taskId);
}
