package com.guagua.dao.question;

import com.guagua.bean.entity.question.ExaminationPaper;
import com.guagua.bean.entity.question.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-4-4 下午7:47
 */
public interface ExaminationPaperDao {

    /* ************************************************
     * insert
     *************************************************/
    Integer insertOne(@Param("paper") ExaminationPaper paper);


    /* ************************************************
     * update
     *************************************************/

    Integer updateStatus(@Param("id") Integer id,
                         @Param("status") Integer status);

    /* ************************************************
     * delete
     *************************************************/
    /* ************************************************
     * select
     *************************************************/

    ExaminationPaper findById(Integer id);

    List<ExaminationPaper> findByBankId(Integer bankId);

    List<ExaminationPaper> findManualByBankId(Integer bankId);
}
