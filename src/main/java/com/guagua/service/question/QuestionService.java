package com.guagua.service.question;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.question.*;

import java.util.List;

/**
 * @author ride
 * @date 18-4-4 上午10:54
 */
public interface QuestionService {

    /**
     * 创建一个题库
     *
     * @param bank
     * @return
     */
    ResultDTO createQuestionBank(QuestionBank bank);

    /**
     * 更新一个题库
     *
     * @param bank
     * @return
     */
    ResultDTO updateQuestionBank(QuestionBank bank);

    /**
     * 删除一个题库
     *
     * @param bankId
     * @return
     */
    ResultDTO deleteQuestionBank(Integer bankId);

    /**
     * 查询用户创建的所有题库
     *
     * @param creator
     * @return
     */
    ResultDTO listQuestionBank(Integer creator, Integer page, Integer size);

    /**
     * 查询摸个题库的详细信息
     *
     * @param bankId
     * @return
     */
    ResultDTO detailQuestionBank(Integer bankId);

    /**
     * 创建一个题目
     *
     * @param question
     * @return
     */
    ResultDTO createQuestion(Question question);

    /**
     * 更新一个题目
     *
     * @param question
     * @return
     */
    ResultDTO updateQuestion(Question question);

    /**
     * 设置问题的标准答案
     *
     * @param bankId
     * @param questionId
     * @param hasAnswer
     * @param answer
     * @return
     */
    ResultDTO setAnswer(Integer bankId, Integer questionId, Integer hasAnswer, String answer);

    /**
     * 给题目添加一个选项
     *
     * @param item
     * @return
     */
    ResultDTO addItem(QuestionItem item);

    /**
     * 删除题目的一条选项
     *
     * @param itemId
     * @return
     */
    ResultDTO deleteItem(Integer questionId, Integer itemId);

    /**
     * 删除一个问题
     *
     * @param questionId
     * @return
     */
    ResultDTO deleteQuestion(Integer questionId);

    /**
     * 查询题库下所有问题
     *
     * @param bankId
     * @param page
     * @param size
     * @return
     */
    ResultDTO listQuestions(Integer bankId, Integer page, Integer size);

    /**
     * 查询题库下指定类型的题目
     *
     * @param bankId
     * @param type
     * @param page
     * @param size
     * @return
     */
    ResultDTO listQuestionsByType(Integer bankId, Integer type, Integer page, Integer size);

    /**
     * 查询问题详情
     *
     * @param questionId
     * @return
     */
    ResultDTO detailQuestion(Integer questionId);

    /**
     * 查询问题的所有选项
     *
     * @param questionId
     * @return
     */
    ResultDTO getItemsByQuestion(Integer questionId);

    /**
     * 手动创建一个
     *
     * @param paper
     * @return
     */
    ResultDTO manualCreateExamination(ExaminationPaper paper);

    /**
     * 创建试卷和题目的联系
     *
     * @param examinationId
     * @param questionId
     * @param type
     * @return
     */
    ResultDTO examinationAddQuestion(Integer examinationId, Integer questionId, Integer type);

    /**
     * 批量建立试卷与问题的联系
     *
     * @param examinationId
     * @param singleList
     * @param multipleList
     * @param judgementList
     * @param textList
     * @return
     */
    ResultDTO examinationAddQuestionBatch(Integer examinationId, List<Integer> singleList, List<Integer> multipleList,
                                          List<Integer> judgementList, List<Integer> textList);

    /**
     * 查询该题库下的而所有试卷
     *
     * @param bankId
     * @return
     */
    ResultDTO listManualExaminationByBankId(Integer bankId, Integer page, Integer size);

    /**
     * 查询单个试卷的详细情况
     *
     * @param examinationId
     * @return
     */
    ResultDTO examinationDetail(Integer examinationId);

    /**
     * 创建一个自动生成的试卷的规则
     *
     * @param rule
     * @return
     */
    ResultDTO createRule(RuleOfGenerationExamination rule);

    /**
     * 删除一条规则
     *
     * @param ruleId
     * @return
     */
    ResultDTO deleteRule(Integer ruleId);

    /**
     * 列出题库下所有的自动生成试卷规则
     *
     * @param bankId
     * @return
     */
    ResultDTO listRulesByBankId(Integer bankId, Integer page, Integer size);


    /**
     * 查询用户创建的所有自动生成试卷的规则
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    ResultDTO listRulesByCreator(Integer userId, Integer page, Integer size);
}
