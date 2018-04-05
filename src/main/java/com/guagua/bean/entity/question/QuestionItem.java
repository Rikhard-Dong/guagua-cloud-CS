package com.guagua.bean.entity.question;

/**
 * @author ride
 * @date 18-4-4 下午12:56
 */
public class QuestionItem {
    private Integer id;
    private Integer questionId;
    private String item;        // 题目序号
    private String content;     // 题目内容
    private String remarks;     // 题目备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "QuestionItem{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", item='" + item + '\'' +
                ", content='" + content + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
