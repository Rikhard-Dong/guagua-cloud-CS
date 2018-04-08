package com.guagua.bean.entity.question;

/**
 * @author ride
 * @date 18-4-6 下午1:05
 * 回答答案
 */
public class Answer {

    private Integer id;
    private Integer sheetId;
    private Integer questionId;
    private String answer;


    public Answer() {
    }

    public Answer(Integer sheetId, Integer questionId) {
        this.sheetId = sheetId;
        this.questionId = questionId;
    }

    public Answer(Integer sheetId, Integer questionId, String answer) {
        this.sheetId = sheetId;
        this.questionId = questionId;
        this.answer = answer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSheetId() {
        return sheetId;
    }

    public void setSheetId(Integer sheetId) {
        this.sheetId = sheetId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", sheetId=" + sheetId +
                ", questionId=" + questionId +
                ", answer='" + answer + '\'' +
                '}';
    }
}
