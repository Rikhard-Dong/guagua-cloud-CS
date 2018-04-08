package com.guagua.dao.question;

import com.guagua.bean.entity.question.RuleOfGenerationExamination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-4-5 下午3:16
 */
public interface RuleOfGenerationExaminationDao {
    /* ************************************************
     * insert
     *************************************************/

    Integer insertOne(@Param("rule") RuleOfGenerationExamination rule);

    /* ************************************************
     * update
     *************************************************/

    /* ************************************************
     * delete
     *************************************************/

    Integer deleteOne(Integer id);

    /* ************************************************
     * select
     *************************************************/

    List<RuleOfGenerationExamination> findByBankId(Integer bankId);

    List<RuleOfGenerationExamination> findByCreator(Integer creator);

    RuleOfGenerationExamination findById(Integer ruleId);
}
