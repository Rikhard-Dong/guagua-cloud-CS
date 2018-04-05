package com.guagua.bean.entity.question;

/**
 * @author ride
 * @date 18-4-4 下午10:34
 * 试卷和题目的联系
 */
public class QuestionExaminationPaper {
    private Integer id;
    private Integer examinationId;
    private Integer questionId;

    public QuestionExaminationPaper() {
    }

    public QuestionExaminationPaper(Integer examinationId, Integer questionId) {
        this.examinationId = examinationId;
        this.questionId = questionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "QuestionExaminationPaper{" +
                "id=" + id +
                ", examinationId=" + examinationId +
                ", questionId=" + questionId +
                '}';
    }
}
