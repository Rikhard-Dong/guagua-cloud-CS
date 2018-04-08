package com.guagua.service.question.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.dto.question.ExaminationPaperDTO;
import com.guagua.bean.dto.question.ExaminationPaperSimpleDTO;
import com.guagua.bean.dto.question.QuestionDTO;
import com.guagua.bean.entity.MyPageInfo;
import com.guagua.bean.entity.common.User;
import com.guagua.bean.entity.question.*;
import com.guagua.dao.common.UserDao;
import com.guagua.dao.question.*;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.question.QuestionService;
import com.guagua.utils.RegExpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author ride
 * @date 18-4-4 上午10:55
 */
@Service("questionService")
@Transactional
public class QuestionServiceImpl extends BaseService implements QuestionService {

    private final UserDao userDao;

    private final QuestionBankDao bankDao;

    private final QuestionDao questionDao;

    private final QuestionItemDao itemDao;

    private final ExaminationPaperDao paperDao;

    private final QuestionExaminationPaperDao questionExaminationPaperDao;

    private final RuleOfGenerationExaminationDao ruleDao;

    @Autowired
    public QuestionServiceImpl(UserDao userDao,
                               QuestionBankDao bankDao,
                               QuestionDao questionDao,
                               QuestionItemDao itemDao,
                               ExaminationPaperDao paperDao,
                               QuestionExaminationPaperDao questionExaminationPaperDao,
                               RuleOfGenerationExaminationDao ruleDao) {
        this.userDao = userDao;
        this.bankDao = bankDao;
        this.questionDao = questionDao;
        this.itemDao = itemDao;
        this.paperDao = paperDao;
        this.questionExaminationPaperDao = questionExaminationPaperDao;
        this.ruleDao = ruleDao;
    }

    // 创建一个题库
    public ResultDTO createQuestionBank(QuestionBank bank) {
        Integer var1 = bankDao.insertOne(bank);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }

        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // 更新一个题库
    public ResultDTO updateQuestionBank(QuestionBank bank) {
        Integer var1 = bankDao.updateOne(bank);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.UPDATE_FAIL);
        }

        return new ResultDTO(DataDictionary.UPDATE_SUCCESS);
    }

    // 删除一个题库
    public ResultDTO deleteQuestionBank(Integer bankId) {
        Integer var1 = bankDao.deleteOne(bankId);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.DELETE_FAIL);
        }
        return new ResultDTO(DataDictionary.DELETE_SUCCESS);
    }

    // 查询所有题库
    public ResultDTO listQuestionBank(Integer creator, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<QuestionBank> banks = bankDao.queryByCreator(creator);
        PageInfo<QuestionBank> info = new PageInfo<QuestionBank>(banks);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("banks", info);
    }

    // 查询单个题库详细信息
    public ResultDTO detailQuestionBank(Integer bankId) {
        QuestionBank bank = bankDao.findById(bankId);
        if (bank == null) {
            throw new CustomException(DataDictionary.QUERY_FAIL);
        }

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("bank", bank);
    }

    // 创建一个题目
    public ResultDTO createQuestion(Question question) {
        Integer var1 = questionDao.insertOne(question);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.INSERT_FAIL);
        }
        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // 更新一个题目
    public ResultDTO updateQuestion(Question question) {
        Integer var1 = questionDao.updateOne(question);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.UPDATE_FAIL);
        }
        return new ResultDTO(DataDictionary.UPDATE_SUCCESS);
    }

    // 设置问题的具体答案
    public ResultDTO setAnswer(Integer bankId, Integer questionId, Integer hasAnswer, String answer) {
        Question question = questionDao.findById(questionId);
        if (question == null) {
            throw new CustomException(DataDictionary.QUERY_FAIL);
        }

        if (hasAnswer == 1) {
            // 只允许问题类型为0, 1, 2 的题目设置标准答案
            if (question.getType() == 3) {
                throw new CustomException(DataDictionary.ERROR).addData("detail", "文字题不能设置标准答案");
            }

            Integer countItems = itemDao.countByQuestionId(questionId);
            if (question.getType() == 0) {  // 单选题
                if (countItems < 2) {
                    throw new CustomException(DataDictionary.ERROR).addData("detail", "单选题必须拥有两个以上选项");
                }
                if (!RegExpUtils.isInteger(answer)) {
                    throw new CustomException(DataDictionary.ERROR).addData("detail", "请确保答案是一个合法的数字");
                }
            } else if (question.getType() == 1) {
                if (countItems < 3) {   // 多选题
                    throw new CustomException(DataDictionary.ERROR).addData("detail", "多选题必须拥有三个以上选项");
                }
                String[] answers = answer.split("-");
                if (answers.length < 2) {
                    throw new CustomException(DataDictionary.ERROR).addData("detail", "多选题必须选择两个及以上答案");
                }
            } else if (question.getType() == 2) { // 判断题

            }
        }

        Integer var1 = questionDao.updateStandardAnswer(questionId, hasAnswer, hasAnswer == 0 ? null : answer);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.UPDATE_FAIL);
        }

        return new ResultDTO(DataDictionary.UPDATE_SUCCESS);
    }

    // 给题目添加一个选项
    public ResultDTO addItem(QuestionItem item) {
        Question question = questionDao.findById(item.getQuestionId());
        if (question == null) {
            throw new CustomException(DataDictionary.QUERY_FAIL).addData("detail", "添加item错误, 问题不存在");
        }

        if (question.getType() != 0 && question.getType() != 1) {
            throw new CustomException(DataDictionary.ERROR).addData("detail", "只有单选题和单选题才能添加选项");
        }

        Integer var1 = itemDao.insertOne(item);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.INSERT_FAIL);
        }

        Integer countItems = itemDao.countByQuestionId(item.getQuestionId());
        if (countItems >= 3) {
            // 更新为合法的题目
            questionDao.updateStatus(item.getQuestionId(), 1);
        } else {
            questionDao.updateStatus(item.getQuestionId(), 0);
        }

        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // 给题目删除一个选项
    public ResultDTO deleteItem(Integer questionId, Integer itemId) {
        Question question = questionDao.findById(questionId);
        if (question == null) {
            throw new CustomException(DataDictionary.QUERY_FAIL).addData("detail", "删除item错误, 问题不存在");
        }

        if (question.getType() != 0 && question.getType() != 1) {
            throw new CustomException(DataDictionary.ERROR).addData("detail", "只有单选题和单选题才能删除选项");
        }

        // 删除这条item
        Integer var1 = itemDao.deleteOne(itemId);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.DELETE_FAIL);
        }

        // 重新判断一下问题的状态是否合法
        Integer countItems = itemDao.countByQuestionId(questionId);
        if (question.getStatus() == 1 && countItems < 3) {
            Integer var2 = questionDao.updateStatus(questionId, 0);
            if (var2 == 0) {
                throw new CustomException(DataDictionary.UPDATE_FAIL);
            }
        }
        // 需要考虑当前选项是否为答案或者为答案之一
        if (question.getHasStandardAnswer() == 1) {
            if (question.getType() == 0) {
                // 单选题的情况
                Integer answerId = Integer.valueOf(question.getStandardAnswer());
                if (answerId.equals(itemId)) {
                    Integer var2 = questionDao.updateStandardAnswer(questionId, 0, null);
                    if (var2 == 0) {
                        throw new CustomException(DataDictionary.UPDATE_FAIL);
                    }
                }
            } else if (question.getType() == 1) {
                // 多选题的情况
                String[] answers = question.getStandardAnswer().split("-");
                boolean flag = false;
                for (String answerId : answers) {
                    if (itemId.equals(Integer.valueOf(answerId))) {
                        flag = true;
                        break;
                    }
                }

                // 要删除的选项在正确答案内
                if (flag) {
                    if (answers.length <= 2) {
                        // 如果多选的标准答案只有两个的话, 则在删除一个表示直接为不设标准答案
                        Integer var2 = questionDao.updateStandardAnswer(questionId, 0, null);
                        if (var2 == 0) {
                            throw new CustomException(DataDictionary.UPDATE_FAIL);
                        }
                    } else {
                        // 移除该选项
                        List<String> answerIds = Arrays.asList(answers);
                        answerIds.remove(itemId);
                        Integer var2 = questionDao.updateStandardAnswer(questionId, 1, splitJoint(answerIds));
                        if (var2 == 0) {
                            throw new CustomException(DataDictionary.UPDATE_FAIL);
                        }
                    }
                }
            }
        }

        return new ResultDTO(DataDictionary.DELETE_SUCCESS);
    }

    // 删除一个问题
    public ResultDTO deleteQuestion(Integer questionId) {
        Integer var1 = questionDao.deleteOne(questionId);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.DELETE_FAIL);
        }
        return new ResultDTO(DataDictionary.DELETE_SUCCESS);
    }

    // 查询题库下所有问题
    public ResultDTO listQuestions(Integer bankId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Question> questions = questionDao.findAllByBankId(bankId);
        PageInfo<Question> info = new PageInfo<Question>(questions);
        List<QuestionDTO> dtos = convert2QuestionDTO(info.getList());
        MyPageInfo<QuestionDTO> myInfo = new MyPageInfo<QuestionDTO>(info);
        myInfo.setList(dtos);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("questions", myInfo);
    }

    // 查询题库下指定类型的问题
    public ResultDTO listQuestionsByType(Integer bankId, Integer type, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Question> questions = questionDao.findAllByBankIdAndType(bankId, type);
        PageInfo<Question> info = new PageInfo<Question>(questions);
        List<QuestionDTO> dtos = convert2QuestionDTO(info.getList());
        MyPageInfo<QuestionDTO> myInfo = new MyPageInfo<QuestionDTO>(info);
        myInfo.setList(dtos);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("questions", myInfo);
    }

    // 查询题库下单个任务详情
    public ResultDTO detailQuestion(Integer questionId) {
        Question question = questionDao.findById(questionId);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS)
                .addData("question", convert2QuestionDTO(question));
    }

    // 得到问题的列表
    public ResultDTO getItemsByQuestion(Integer questionId) {
        List<QuestionItem> items = itemDao.findByQuestionId(questionId);
        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("items", items);
    }

    /* ************************
     * 试卷部分
     *
     **********************/

    // 创建一个手动试卷
    public ResultDTO manualCreateExamination(ExaminationPaper paper) {
        Integer num = paper.getSingleNum() + paper.getMultipleNum() + paper.getJudgementNum() + paper.getTextNum();
        paper.setNum(num);
        Integer var1 = paperDao.insertOne(paper);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.INSERT_FAIL);
        }
        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // 添加一条试卷和题目的联系
    public ResultDTO examinationAddQuestion(Integer examinationId, Integer questionId, Integer type) {
        // 判断试卷的状态是否为0
        ExaminationPaper paper = paperDao.findById(examinationId);
        if (paper.getStatus() == 1) {
            throw new CustomException(DataDictionary.ERROR).addData("detail", "试卷状态为生效状态, 无法添加题目");
        }

        judgeQuestionIsLegal(questionId, type);

        // 首先根据type查询是否还需要添加题目
        Integer count = questionExaminationPaperDao.countByQuestionTypeAndExamination(examinationId, type);
        if (count >= paper.getNumByType(type)) {
            throw new CustomException(DataDictionary.ERROR).addData("detail", "该类型的题目已达到要求数量,无法继续添加");
        }
        Integer var1 = questionExaminationPaperDao.insertOne(new QuestionExaminationPaper(examinationId, questionId));

        if (var1 == 0) {
            throw new CustomException(DataDictionary.INSERT_FAIL);
        }

        updateStatus(paper);

        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // 批量建立联系
    public ResultDTO examinationAddQuestionBatch(Integer examinationId, List<Integer> singleList, List<Integer> multipleList,
                                                 List<Integer> judgementList, List<Integer> textList) {
        Map<String, Object> detail = new HashMap<String, Object>();

        ExaminationPaper paper = paperDao.findById(examinationId);
        if (paper == null) {
            detail.put("detail", "试卷不存在");
            throw new CustomException(DataDictionary.ERROR, detail);
        }

        // 批量插入各种类型的问题到试卷中

        if (singleList != null && singleList.size() > 0) {
            Integer count = questionExaminationPaperDao.countByQuestionTypeAndExamination(examinationId, 0);
            if (singleList.size() <= paper.getSingleNum() - count) {
                for (Integer questionId : singleList) {
                    judgeQuestionIsLegal(questionId, 0);
                    Integer var = questionExaminationPaperDao.insertOne(new QuestionExaminationPaper(examinationId, questionId));
                    if (var == 0) {
                        throw new CustomException(DataDictionary.INSERT_FAIL);
                    }
                }
            } else {
                detail.put("detail", "单选题超出数量");
                throw new CustomException(DataDictionary.ERROR, detail);
            }
        }

        if (multipleList != null && multipleList.size() > 0) {
            Integer count = questionExaminationPaperDao.countByQuestionTypeAndExamination(examinationId, 1);
            if (multipleList.size() <= paper.getMultipleNum() - count) {
                for (Integer questionId : multipleList) {
                    judgeQuestionIsLegal(questionId, 1);
                    Integer var = questionExaminationPaperDao.insertOne(new QuestionExaminationPaper(examinationId, questionId));
                    if (var == 0) {
                        throw new CustomException(DataDictionary.INSERT_FAIL);
                    }
                }
            } else {
                detail.put("detail", "多选题超出数量");
                throw new CustomException(DataDictionary.ERROR, detail);
            }
        }

        if (judgementList != null && judgementList.size() > 0) {
            Integer count = questionExaminationPaperDao.countByQuestionTypeAndExamination(examinationId, 2);
            if (judgementList.size() <= paper.getJudgementNum() - count) {
                for (Integer questionId : judgementList) {
                    judgeQuestionIsLegal(questionId, 2);
                    Integer var = questionExaminationPaperDao.insertOne(new QuestionExaminationPaper(examinationId, questionId));
                    if (var == 0) {
                        throw new CustomException(DataDictionary.INSERT_FAIL);
                    }
                }
            } else {
                detail.put("detail", "判断题超出数量");
                throw new CustomException(DataDictionary.ERROR, detail);

            }
        }

        if (textList != null && textList.size() > 0) {
            Integer count = questionExaminationPaperDao.countByQuestionTypeAndExamination(examinationId, 3);
            if (textList.size() <= paper.getTextNum() - count) {
                for (Integer questionId : textList) {
                    judgeQuestionIsLegal(questionId, 3);
                    Integer var = questionExaminationPaperDao.insertOne(new QuestionExaminationPaper(examinationId, questionId));
                    if (var == 0) {
                        throw new CustomException(DataDictionary.INSERT_FAIL);
                    }
                }
            } else {
                detail.put("detail", "文字题超出数量");
                throw new CustomException(DataDictionary.ERROR, detail);
            }
        }

        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // 查询该题库下的所有试卷
    public ResultDTO listManualExaminationByBankId(Integer bankId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<ExaminationPaper> papers = paperDao.findManualByBankId(bankId);
        PageInfo<ExaminationPaper> info = new PageInfo<ExaminationPaper>(papers);
        MyPageInfo<ExaminationPaperSimpleDTO> myInfo = new MyPageInfo<ExaminationPaperSimpleDTO>(info);
        myInfo.setList(convert2ExaminationPaperDTO(info.getList()));

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("papers", myInfo);
    }
    // 查询单个试卷的详细信息

    public ResultDTO examinationDetail(Integer examinationId) {
        ExaminationPaper paper = paperDao.findById(examinationId);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS)
                .addData("examination", convert2ExaminationPaperDTO(paper));
    }

    // 自动生成试卷的规则
    public ResultDTO createRule(RuleOfGenerationExamination rule) {
        Integer num = rule.getSingleNum() + rule.getMultipleNum() + rule.getJudgementNum() + rule.getTextNum();

        rule.setNum(num);

        Integer var1 = ruleDao.insertOne(rule);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.SQL_OPERATION_EXCEPTION);
        }
        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // 删除一条记录
    public ResultDTO deleteRule(Integer ruleId) {

        Integer var1 = ruleDao.deleteOne(ruleId);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.DELETE_FAIL);
        }

        return new ResultDTO(DataDictionary.DELETE_SUCCESS);
    }

    // 查询知识库下的规则
    public ResultDTO listRulesByBankId(Integer bankId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<RuleOfGenerationExamination> rules = ruleDao.findByBankId(bankId);
        PageInfo<RuleOfGenerationExamination> info = new PageInfo<RuleOfGenerationExamination>(rules);
        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("rules", info);
    }

    // 查询用户所创建的规则
    public ResultDTO listRulesByCreator(Integer userId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<RuleOfGenerationExamination> rules = ruleDao.findByCreator(userId);
        PageInfo<RuleOfGenerationExamination> info = new PageInfo<RuleOfGenerationExamination>(rules);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("rules", info);
    }

    /**
     * 拼接itemId
     *
     * @param list
     * @return
     */
    private String splitJoint(List<String> list) {
        if (list == null || list.size() < 2) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        Boolean isFirst = true;
        for (String answer : list) {
            if (isFirst) {
                result.append(answer);
                isFirst = false;
                continue;
            }
            result.append("-").append(answer);
        }

        return result.toString();
    }

    /**
     * 判断问题是否存在及问题的类型是否符合
     *
     * @param questionId
     * @param type
     */
    private void judgeQuestionIsLegal(Integer questionId, Integer type) {
        Map<String, Object> detail = new HashMap<String, Object>();
        Question question = questionDao.findById(questionId);
        if (question == null) {
            detail.put("detail", "问题id#" + questionId + "#不存在");
            throw new CustomException(DataDictionary.ERROR, detail);
        }

        if (!question.getType().equals(type)) {
            detail.put("detail", "问题#" + questionId + "#类型不符合");
            throw new CustomException(DataDictionary.ERROR, detail);
        }

        if (question.getStatus() == 0) {
            detail.put("detail", "问题#" + questionId + "#还未生效");
            throw new CustomException(DataDictionary.ERROR, detail);
        }
    }


    /**
     * 将question转换为dto
     *
     * @param question
     * @return
     */
    private QuestionDTO convert2QuestionDTO(Question question) {
        QuestionBank bank = bankDao.findById(question.getBankId());

        return convert2QuestionDTO(question, bank.getBankName());
    }

    /**
     * 将question转换为dto
     *
     * @param question
     * @param bankName
     * @return
     */
    private QuestionDTO convert2QuestionDTO(Question question, String bankName) {
        if (question == null) {
            return null;
        }

        QuestionDTO dto = new QuestionDTO(question);
        dto.setBankName(bankName);
        if (question.getType() == 2) {
            // 判断题
            List<QuestionItem> items = new ArrayList<QuestionItem>();
            items.add(itemDao.findById(0));
            items.add(itemDao.findById(1));
            dto.setItems(items);
            if (question.getHasStandardAnswer() == 1) {
                List<Integer> answerIds = new ArrayList<Integer>();
                if (question.getStandardAnswer().equals("true")) {
                    answerIds.add(0);
                } else if (question.getStandardAnswer().equals("false")) {
                    answerIds.add(1);
                }
                dto.setAnswerIds(answerIds);
            }
        } else if (question.getType() == 0 || question.getType() == 1) {
            // 单选题或者多选题
            List<QuestionItem> items = itemDao.findByQuestionId(question.getId());
            dto.setItems(items);

            if (question.getHasStandardAnswer() == 1) {
                List<Integer> answerIds = new ArrayList<Integer>();
                String[] answerIdsStr = question.getStandardAnswer().split("-");
                if (answerIdsStr.length > 0) {
                    for (String idStr : answerIdsStr) {
                        answerIds.add(Integer.valueOf(idStr));
                    }
                    dto.setAnswerIds(answerIds);
                }
            }
        }

        return dto;
    }

    /**
     * 将question转换为dto
     *
     * @param questions
     * @return
     */
    private List<QuestionDTO> convert2QuestionDTO(List<Question> questions) {
        if (questions == null || questions.size() <= 0) {
            return null;
        }
        List<QuestionDTO> dtos = new ArrayList<QuestionDTO>();
        for (Question question : questions) {
            dtos.add(convert2QuestionDTO(question));
        }

        return dtos;
    }

    /**
     * 更新试卷是否生效
     *
     * @param paper
     */
    private void updateStatus(ExaminationPaper paper) {
        Integer status = 1;
        for (Integer i = 0; i < 4; i++) {
            Integer count = questionExaminationPaperDao.countByQuestionTypeAndExamination(paper.getId(), i);
            if (count < paper.getNumByType(i)) {
                status = 0;
                break;
            }
        }

        if (!status.equals(paper.getStatus())) {
            paperDao.updateStatus(paper.getId(), status);
        }
    }

    /**
     * 将paper 转换为dto
     *
     * @param paper
     * @return
     */
    private ExaminationPaperDTO convert2ExaminationPaperDTO(ExaminationPaper paper) {
        if (paper == null) {
            return null;
        }

        ExaminationPaperDTO result = new ExaminationPaperDTO(paper);
        // 创建者名字
        User creator = userDao.findById(paper.getCreator());
        if (creator != null) {
            result.setCreatorName(creator.getUsername());
        }
        // 所属题库名称
        QuestionBank bank = bankDao.findById(paper.getBankId());
        if (bank != null) {
            result.setBankName(bank.getBankName());
        }

        // 题目添加情况
        List<Question> singleQuestions = questionExaminationPaperDao
                .findByQuestionTypeAndExamination(paper.getId(), 0);
        List<Question> multipleQuestions = questionExaminationPaperDao
                .findByQuestionTypeAndExamination(paper.getId(), 1);
        List<Question> judgementQuestions = questionExaminationPaperDao
                .findByQuestionTypeAndExamination(paper.getId(), 2);
        List<Question> textQuestions = questionExaminationPaperDao
                .findByQuestionTypeAndExamination(paper.getId(), 3);

        result.setSingleCurNum(singleQuestions == null ? 0 : singleQuestions.size());
        if (singleQuestions != null && singleQuestions.size() > 0) {
            for (Question question : singleQuestions) {
                List<QuestionItem> items = itemDao.findByQuestionId(question.getId());
                question.setItems(items);
            }
        }
        result.setSingleQuestions(singleQuestions);

        result.setMultipleCurNum(multipleQuestions == null ? 0 : multipleQuestions.size());
        if (multipleQuestions != null && multipleQuestions.size() > 0) {
            for (Question question : multipleQuestions) {
                List<QuestionItem> items = itemDao.findByQuestionId(question.getId());
                question.setItems(items);
            }
        }
        result.setMultipleQuestions(multipleQuestions);

        result.setJudgementCurNum(judgementQuestions == null ? 0 : judgementQuestions.size());
        if (judgementQuestions != null && judgementQuestions.size() > 0) {
            List<QuestionItem> items = new ArrayList<QuestionItem>();
            items.add(itemDao.findById(1));
            items.add(itemDao.findById(0));
            for (Question question : judgementQuestions) {
                question.setItems(items);
            }
        }
        result.setJudgementQuestions(judgementQuestions);

        result.setTextCurNum(textQuestions == null ? 0 : textQuestions.size());
        result.setTextQuestions(textQuestions);

        return result;
    }

    /**
     * 列表转换
     *
     * @param papers
     * @return
     */
    private List<ExaminationPaperSimpleDTO> convert2ExaminationPaperDTO(List<ExaminationPaper> papers) {
        if (papers == null || papers.size() <= 0) {
            return null;
        }
        List<ExaminationPaperSimpleDTO> result = new ArrayList<ExaminationPaperSimpleDTO>();
        for (ExaminationPaper paper : papers) {
            result.add(convert2ExaminationPaperDTO(paper));

        }
        return result;
    }

}
