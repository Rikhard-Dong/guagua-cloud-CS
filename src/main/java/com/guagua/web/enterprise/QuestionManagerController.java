package com.guagua.web.enterprise;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.question.*;
import com.guagua.service.question.QuestionService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ride
 * @date 18-4-4 上午10:56
 * <p>
 * 关于题库和在线测试的控制器
 */
@RestController
@RequestMapping("/enterprise/question_bank")
public class QuestionManagerController extends BaseController {

    private final QuestionService questionService;

    @Autowired
    public QuestionManagerController(QuestionService questionService) {
        this.questionService = questionService;
    }


    /* *******************************************
     * 题库操作
     **********************************************/

    /**
     * 创建一个题库
     *
     * @param bank    bank
     * @param request request
     * @return result dto
     */
    @PostMapping("/create")
    public ResultDTO createQuestionBank(QuestionBank bank,
                                        HttpServletRequest request) {

        bank.setCreator(getUserId(request));
        logger.info("bank ===> {}", bank);

        return questionService.createQuestionBank(bank);
    }

    /**
     * 更新题库
     *
     * @param bankId  bank id
     * @param bank    bank
     * @param request request
     * @return result dto
     */
    @PutMapping("/{bankId}/update")
    public ResultDTO updateQuestionBank(@PathVariable("bankId") Integer bankId,
                                        QuestionBank bank,
                                        HttpServletRequest request) {
        bank.setId(bankId);
        return questionService.updateQuestionBank(bank);
    }

    /**
     * 删除题库
     *
     * @param bankId  bank id
     * @param request request
     * @return result dto
     */
    @DeleteMapping("/{bankId}/delete")
    public ResultDTO deleteQuestionBank(@PathVariable("bankId") Integer bankId,
                                        HttpServletRequest request) {
        return questionService.deleteQuestionBank(bankId);
    }

    /**
     * 查询该用户的所有题库
     *
     * @param request request
     * @return result dto
     */
    @GetMapping("/list")
    public ResultDTO listQuestionBank(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "size", defaultValue = "30") Integer size,
                                      HttpServletRequest request) {
        return questionService.listQuestionBank(getUserId(request), page, size);
    }

    /**
     * 查询某个题库的信息
     *
     * @param bankId  bank id
     * @param request request
     * @return result dto
     */
    @GetMapping("/{bankId}/detail")
    public ResultDTO queryDetailBank(@PathVariable("bankId") Integer bankId,
                                     HttpServletRequest request) {
        return questionService.detailQuestionBank(bankId);
    }

    /* *******************************************
     * 题库问题操作
     **********************************************/

    /**
     * 创建一个题目并和知识库绑定
     *
     * @param bankId   bank id
     * @param question question
     * @param request  request
     * @return result dto
     */
    @PostMapping("/{bankId}/question/create")
    public ResultDTO createQuestion(@PathVariable("bankId") Integer bankId,
                                    Question question,
                                    HttpServletRequest request) {
        question.setBankId(bankId);
        question.setCreator(getUserId(request));
        // 单选题默认不是合法的题目, 必须选项达到一定题目才是合法的题目
        if (question.getType() == 0 || question.getType() == 1) {
            question.setStatus(0);
        } else {
            question.setStatus(1);
        }

        logger.info("question ===> {}", question);

        return questionService.createQuestion(question);
    }

    /**
     * 更新一个题目
     *
     * @param bankId     bank id
     * @param questionId question id
     * @param question   question
     * @param request    request
     * @return result dto
     */
    @PutMapping("/{bankId}/question/{questionId}/update")
    public ResultDTO updateQuestion(@PathVariable("bankId") Integer bankId,
                                    @PathVariable("questionId") Integer questionId,
                                    Question question,
                                    HttpServletRequest request) {
        question.setBankId(bankId);
        question.setId(questionId);

        return questionService.updateQuestion(question);
    }

    /**
     * 删除一个问题
     *
     * @param bankId     bank id
     * @param questionId question id
     * @param request    request
     * @return result dto
     */
    @DeleteMapping("/{bankId}/question/{questionId}/delete")
    public ResultDTO deleteQuestion(@PathVariable("bankId") Integer bankId,
                                    @PathVariable("questionId") Integer questionId,
                                    HttpServletRequest request) {
        return questionService.deleteQuestion(questionId);
    }

    /**
     * 查询题库下的所以所有问题
     *
     * @param bankId  bank id
     * @param request request
     * @return result dto
     */
    @GetMapping("/{bankId}/question/list")
    public ResultDTO listQuestions(@PathVariable("bankId") Integer bankId,
                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", defaultValue = "30") Integer size,
                                   HttpServletRequest request) {

        return questionService.listQuestions(bankId, page, size);
    }

    /**
     * 查询题库下指定类型的题目
     *
     * @param bankId  bank id
     * @param type    type
     * @param page    page
     * @param size    size
     * @param request request
     * @return result dto
     */
    @GetMapping("/{bankId}/question/list/type/{type}")
    public ResultDTO listQuestionsByType(@PathVariable("bankId") Integer bankId,
                                         @PathVariable("type") Integer type,
                                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "size", defaultValue = "30") Integer size,
                                         HttpServletRequest request) {
        return questionService.listQuestionsByType(bankId, type, page, size);
    }

    /**
     * 查询某个具体任务
     *
     * @param bankId     bank id
     * @param questionId question id
     * @param request    request
     * @return result dto
     */
    @GetMapping("/{bankId}/question/{questionId}/detail")
    public ResultDTO detailQuestion(@PathVariable("bankId") Integer bankId,
                                    @PathVariable("questionId") Integer questionId,
                                    HttpServletRequest request) {

        return questionService.detailQuestion(questionId);
    }


    /**
     * 在任务下创建一个选项
     *
     * @param bankId     bank id
     * @param questionId question id
     * @param item       item
     * @return result dto
     */
    @PostMapping("/{bankId}/question/{questionId}/item/create")
    public ResultDTO addItemForQuestion(@PathVariable("bankId") Integer bankId,
                                        @PathVariable("questionId") Integer questionId,
                                        QuestionItem item) {
        item.setQuestionId(questionId);
        return questionService.addItem(item);
    }

    /**
     * 删除一个选项
     *
     * @param bankId     bank id
     * @param questionId question id
     * @param itemId     item id
     * @param request    request
     * @return result dto
     */
    @DeleteMapping("/{bankId}/question/{questionId}/item/{itemId}/delete")
    public ResultDTO deleteItemForQuestion(@PathVariable("bankId") Integer bankId,
                                           @PathVariable("questionId") Integer questionId,
                                           @PathVariable("itemId") Integer itemId,
                                           HttpServletRequest request) {
        return questionService.deleteItem(questionId, itemId);
    }


    /**
     * 设置一个问题的答案
     *
     * @param bankId     bank id
     * @param questionId question id
     * @param hasAnswer  has answer
     * @param answer     answer
     * @param request    request
     * @return result dto
     */
    @PutMapping("/{bankId}/question/{questionId}/set_answer")
    public ResultDTO setAnswer(@PathVariable("bankId") Integer bankId,
                               @PathVariable("questionId") Integer questionId,
                               @RequestParam("hasAnswer") Integer hasAnswer,
                               @RequestParam(value = "answer", required = false) String answer,
                               HttpServletRequest request) {

        return questionService.setAnswer(bankId, questionId, hasAnswer, answer);
    }

    /**
     * 查询一个问题的所有选项
     *
     * @param bankId     bank id
     * @param questionId question id
     * @param request    request
     * @return result dto
     */
    @GetMapping("/{bankId}/question/{questionId}/items")
    public ResultDTO getItemsByQuestion(@PathVariable("bankId") Integer bankId,
                                        @PathVariable("questionId") Integer questionId,
                                        HttpServletRequest request) {
        return questionService.getItemsByQuestion(questionId);
    }



    /* *******************************************
     * 生成试卷相关操作
     **********************************************/

    /**
     * 手动创建试卷, 试卷类型一定为1(手动), 试卷状态一定为0(未启用)
     *
     * @param bankId  bank id
     * @param paper   paper
     * @param request request
     * @return result dto
     */
    @PostMapping("/{bankId}/examination/create/manual")
    public ResultDTO createExamination(@PathVariable("bankId") Integer bankId,
                                       ExaminationPaper paper,
                                       HttpServletRequest request) {
        logger.info("examination paper =========> {}", paper);

        paper.setBankId(bankId);
        paper.setCreator(getUserId(request));
        paper.setType(1);
        paper.setStatus(0);
        return questionService.manualCreateExamination(paper);
    }


    /**
     * 建立试卷题目联系 单条
     *
     * @param bankId        bank id
     * @param examinationId examination id
     * @param type          type
     * @param questionId    question id
     * @param request       request
     * @return result dto
     */
    @PostMapping("/{bankId}/examination/{examinationId}/question/add")
    public ResultDTO examinationAddQuestion(@PathVariable("bankId") Integer bankId,
                                            @PathVariable("examinationId") Integer examinationId,
                                            @RequestParam("type") Integer type,
                                            @RequestParam("questionId") Integer questionId,
                                            HttpServletRequest request) {

        return questionService.examinationAddQuestion(examinationId, questionId, type);
    }

    /**
     * 建立批量的联系
     *
     * @param bankId        bank id
     * @param examinationId examination id
     * @param singleList    single list
     * @param multipleList  multiple list
     * @param judgementList judgement list
     * @param textList      text list
     * @param request       request
     * @return result dto
     */
    @PostMapping("/{bankId}/examination/{examinationId}/question/add/batch")
    public ResultDTO examinationAddQuestionBatch(@PathVariable("bankId") Integer bankId,
                                                 @PathVariable("examinationId") Integer examinationId,
                                                 @RequestParam(value = "singleList[]", required = false) List<Integer> singleList,
                                                 @RequestParam(value = "multipleList[]", required = false) List<Integer> multipleList,
                                                 @RequestParam(value = "judgementList[]", required = false) List<Integer> judgementList,
                                                 @RequestParam(value = "textList[]", required = false) List<Integer> textList,
                                                 HttpServletRequest request) {
        logger.info("\nsingle list ===>{}\nmultipleList ===> {}\njudgementList ===> {}\n textList ===> {}",
                singleList, multipleList, judgementList, textList);


        return questionService.examinationAddQuestionBatch(examinationId, singleList, multipleList, judgementList, textList);
    }


    /**
     * 手动创建试卷
     *
     * @param bankId bank id
     * @param page   page
     * @param size   size
     * @return result dto
     */
    @GetMapping("/{bankId}/examination/list/manual")
    public ResultDTO listManualExamination(@PathVariable("bankId") Integer bankId,
                                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "30") Integer size) {

        return questionService.listManualExaminationByBankId(bankId, page, size);
    }


    /**
     * 查询某个试卷详细信息
     *
     * @param bankId        bank id
     * @param examinationId examination id
     * @param request       request
     * @return result dto
     */
    @GetMapping("/{bankId}/examination/{examinationId}/detail")
    public ResultDTO examinationDetail(@PathVariable("bankId") Integer bankId,
                                       @PathVariable("examinationId") Integer examinationId,
                                       HttpServletRequest request) {
        return questionService.examinationDetail(examinationId);
    }

    /* *******************************************
     * 自动生成试卷的规则操作
     **********************************************/


    /**
     * 添加一条自动生成试卷的规则
     *
     * @param bankId  bank id
     * @param rule    rule
     * @param request request
     * @return result dto
     */
    @PostMapping("/{bankId}/rule/create")
    public ResultDTO createRule(@PathVariable("bankId") Integer bankId,
                                RuleOfGenerationExamination rule,
                                HttpServletRequest request) {
        rule.setBankId(bankId);
        rule.setCreator(getUserId(request));

        return questionService.createRule(rule);
    }

    /**
     * 删除一条
     *
     * @param bankId  bank id
     * @param ruleId  rule id
     * @param request request
     * @return result dto
     */
    @DeleteMapping("/{bankId}/rule/{ruleId}/delete")
    public ResultDTO deleteRule(@PathVariable("bankId") Integer bankId,
                                @PathVariable("ruleId") Integer ruleId,
                                HttpServletRequest request) {

        return questionService.deleteRule(ruleId);
    }

    /**
     * 查询题库下的所有生成规则
     *
     * @param bankId  bank id
     * @param page    page
     * @param size    size
     * @param request request
     * @return result dto
     */
    @GetMapping("/{bankId}/rule/list")
    public ResultDTO listRules(@PathVariable("bankId") Integer bankId,
                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "size", defaultValue = "30") Integer size,
                               HttpServletRequest request) {

        return questionService.listRulesByBankId(bankId, page, size);
    }

    /**
     * 查询用户的所有规则
     *
     * @param page    page
     * @param size    size
     * @param request request
     * @return result dto
     */
    @GetMapping("/rule/list")
    public ResultDTO listRulesByCreator(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "size", defaultValue = "30") Integer size,
                                        HttpServletRequest request) {
        return questionService.listRulesByCreator(getUserId(request), page, size);
    }
}
