package com.guagua.bean.dto.question;

import com.guagua.bean.entity.common.Task;
import com.guagua.bean.entity.question.AnswerSheet;
import com.guagua.bean.entity.question.ExaminationPaper;
import com.guagua.bean.entity.question.Question;
import com.guagua.utils.DateUtils;

import java.util.List;

/**
 * @author ride
 * @date 18-4-6 下午7:24
 */
public class AnswerSheetDTO extends ExaminationPaperSimpleDTO {

    private Integer sheetId;
    private Integer answerId;
    private Integer taskId;
    private String answer;
    private String taskTitle;
    private Integer score;
    private String remark;
    private String sheetCreateTime;
    private String evaluationTime;
    private String evaluationStatus;


    private List<QuestionDTO> singleQuestions;
    private List<QuestionDTO> multipleQuestions;
    private List<QuestionDTO> judgementQuestions;
    private List<QuestionDTO> textQuestions;

    public AnswerSheetDTO() {
    }

    public AnswerSheetDTO(ExaminationPaper paper) {
        super(paper);
    }

    public AnswerSheetDTO(ExaminationPaper paper, AnswerSheet sheet) {
        super(paper);
        this.sheetId = sheet.getId();
        this.taskId = sheet.getTaskId();
        this.score = sheet.getScore();
        this.remark = sheet.getRemark();
        this.sheetCreateTime = DateUtils.date2StrCN(sheet.getCreateTime());
        this.evaluationTime = DateUtils.date2StrCN(sheet.getMarkTime());
        this.evaluationStatus = sheet.getEvaluationStatus() == 0 ? "已评价" : "未评价";
    }


    public Integer getSheetId() {
        return sheetId;
    }

    public void setSheetId(Integer sheetId) {
        this.sheetId = sheetId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public List<QuestionDTO> getSingleQuestions() {
        return singleQuestions;
    }

    public void setSingleQuestions(List<QuestionDTO> singleQuestions) {
        this.singleQuestions = singleQuestions;
    }

    public List<QuestionDTO> getMultipleQuestions() {
        return multipleQuestions;
    }

    public void setMultipleQuestions(List<QuestionDTO> multipleQuestions) {
        this.multipleQuestions = multipleQuestions;
    }

    public List<QuestionDTO> getJudgementQuestions() {
        return judgementQuestions;
    }

    public void setJudgementQuestions(List<QuestionDTO> judgementQuestions) {
        this.judgementQuestions = judgementQuestions;
    }

    public List<QuestionDTO> getTextQuestions() {
        return textQuestions;
    }

    public void setTextQuestions(List<QuestionDTO> textQuestions) {
        this.textQuestions = textQuestions;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSheetCreateTime() {
        return sheetCreateTime;
    }

    public void setSheetCreateTime(String sheetCreateTime) {
        this.sheetCreateTime = sheetCreateTime;
    }

    public String getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(String evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public String getEvaluationStatus() {
        return evaluationStatus;
    }

    public void setEvaluationStatus(String evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }

    @Override
    public String toString() {
        return "AnswerSheetDTO{" +
                "sheetId=" + sheetId +
                ", answerId=" + answerId +
                ", taskId=" + taskId +
                ", answer='" + answer + '\'' +
                ", taskTitle='" + taskTitle + '\'' +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                ", sheetCreateTime='" + sheetCreateTime + '\'' +
                ", evaluationTime='" + evaluationTime + '\'' +
                ", singleQuestions=" + singleQuestions +
                ", multipleQuestions=" + multipleQuestions +
                ", judgementQuestions=" + judgementQuestions +
                ", textQuestions=" + textQuestions +
                ", id=" + id +
                ", bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                ", creator=" + creator +
                ", creatorName='" + creatorName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", num=" + num +
                ", singleNum=" + singleNum +
                ", singleCurNum=" + singleCurNum +
                ", multipleNum=" + multipleNum +
                ", multipleCurNum=" + multipleCurNum +
                ", judgementNum=" + judgementNum +
                ", judgementCurNum=" + judgementCurNum +
                ", textNum=" + textNum +
                ", textCurNum=" + textCurNum +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
