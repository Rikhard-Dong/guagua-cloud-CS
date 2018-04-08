package com.guagua.service.question.impl;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.dto.question.AnswerSheetDTO;
import com.guagua.bean.dto.question.QuestionDTO;
import com.guagua.bean.entity.common.Task;
import com.guagua.bean.entity.question.*;
import com.guagua.dao.common.TaskDao;
import com.guagua.dao.question.*;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.question.SheetOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ride
 * @date 18-4-6 下午8:14
 */
@Service("sheetOperationService")
public class SheetOperationServiceImpl extends BaseService implements SheetOperationService {

    private final TaskDao taskDao;

    private final AnswerSheetDao sheetDao;

    private final ExaminationPaperDao paperDao;

    private final AnswerDao answerDao;

    private final QuestionDao questionDao;

    private final QuestionItemDao itemDao;

    @Autowired
    public SheetOperationServiceImpl(TaskDao taskDao,
                                     AnswerSheetDao sheetDao,
                                     ExaminationPaperDao paperDao,
                                     AnswerDao answerDao,
                                     QuestionDao questionDao,
                                     QuestionItemDao itemDao) {
        this.taskDao = taskDao;
        this.sheetDao = sheetDao;
        this.paperDao = paperDao;
        this.answerDao = answerDao;
        this.questionDao = questionDao;
        this.itemDao = itemDao;
    }

    // 得到客服在该任务下的笔试结果
    public ResultDTO getSheetByTaskIdAndAnswerId(Integer taskId, Integer answerId) {
        Task task = taskDao.findByTaskId(taskId);
        if (task == null) {
            throw new CustomException(DataDictionary.TASK_NOT_EXISTS);
        }
        if (task.getIsNeedExamination() == 0) {
            throw new CustomException(DataDictionary.ERROR)
                    .addData("detail", "此任务不需要客服笔试");
        }

        AnswerSheet answerSheet = sheetDao.findByAnswererAndTaskId(answerId, taskId);
        if (answerSheet == null) {
            throw new CustomException(DataDictionary.ERROR)
                    .addData("detail", "该客服没有提交答卷");
        }

        ExaminationPaper paper = paperDao.findById(answerSheet.getPaperId());
        if (paper == null) {
            throw new CustomException(DataDictionary.ERROR)
                    .addData("detail", "对应的试卷不存在");
        }

        return new ResultDTO(DataDictionary.QUERY_SUCCESS)
                .addData("sheet", convert2SheetDTO(paper, answerSheet));
    }

    // 给答卷评分
    public ResultDTO scoreSheet(Integer sheetId, Integer score, String remark) {
        AnswerSheet sheet = sheetDao.findById(sheetId);
        if (sheet == null) {
            throw new CustomException(DataDictionary.ERROR)
                    .addData("detail", "答卷不存在");
        }
        if (score > 10 || score < 0) {
            throw new CustomException(DataDictionary.ERROR)
                    .addData("detail", "评分必须在0-10分之间");
        }

        Date curDate = new Date();
        Integer var1 = sheetDao.updateScore(sheetId, score, remark, curDate, 1);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.UPDATE_FAIL);
        }

        return new ResultDTO(DataDictionary.UPDATE_SUCCESS);
    }

    /**
     * 转换
     *
     * @param paper
     * @param sheet
     * @return
     */
    private AnswerSheetDTO convert2SheetDTO(ExaminationPaper paper, AnswerSheet sheet) {
        if (paper == null || sheet == null) {
            return null;
        }
        AnswerSheetDTO result = new AnswerSheetDTO(paper, sheet);
        List<Answer> answers = answerDao.findBySheetId(sheet.getId());
        // 各个类型的题目
        List<QuestionDTO> singleQuesions = new ArrayList<QuestionDTO>();
        List<QuestionDTO> multipleQuestions = new ArrayList<QuestionDTO>();
        List<QuestionDTO> judgementQuestions = new ArrayList<QuestionDTO>();
        List<QuestionDTO> textQuestions = new ArrayList<QuestionDTO>();

        // 判断题的两个选项
        List<QuestionItem> judgementItems = new ArrayList<QuestionItem>();
        QuestionItem trueItem = itemDao.findById(1);
        QuestionItem falseItem = itemDao.findById(0);
        judgementItems.add(trueItem);
        judgementItems.add(falseItem);

        for (Answer answer : answers) {
            Question question = questionDao.findById(answer.getQuestionId());
            if (question == null) {
                continue;
            }
            QuestionDTO questionDTO = new QuestionDTO(question);
            questionDTO.setAnswer(answer.getAnswer());
            switch (question.getType()) {
                case 0:
                    List<QuestionItem> items = itemDao.findByQuestionId(question.getId());
                    questionDTO.setItems(items);
                    singleQuesions.add(questionDTO);
                    break;
                case 1:
                    List<QuestionItem> items1 = itemDao.findByQuestionId(question.getId());
                    questionDTO.setItems(items1);
                    multipleQuestions.add(questionDTO);
                    break;
                case 2:
                    questionDTO.setItems(judgementItems);
                    judgementQuestions.add(questionDTO);
                    break;
                case 3:
                    textQuestions.add(questionDTO);
                    break;
            }
        }

        result.setSingleQuestions(singleQuesions);
        result.setMultipleQuestions(multipleQuestions);
        result.setJudgementQuestions(judgementQuestions);
        result.setTextQuestions(textQuestions);

        return result;
    }
}
