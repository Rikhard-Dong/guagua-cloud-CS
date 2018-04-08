package com.guagua.bean.entity.question;

import java.util.Date;
import java.util.List;

/**
 * @author ride
 * @date 18-4-4 下午12:47
 */
public class Question {
    private Integer id;
    private Integer bankId;
    private Integer creator;
    private String question;
    private String remarks;
    private Integer type;       // 0 单选题 1 多选题 2 判断题 3 文字题
    private Integer hasStandardAnswer;  // 是否有标准答案 0 没有 1 有
    private String standardAnswer;     // 对应itemId, 如果是多选题的话, 使用'-'拼接id
    private Integer status;         // 题目生效是否 0 不生效, 1 生效, 判断题和文字题默认生效, 如果是单选题和多选题, 则需要选项达到3个才生效
    private Date createTime;

    private List<QuestionItem> items;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHasStandardAnswer() {
        return hasStandardAnswer;
    }

    public void setHasStandardAnswer(Integer hasStandardAnswer) {
        this.hasStandardAnswer = hasStandardAnswer;
    }

    public String getStandardAnswer() {
        return standardAnswer;
    }

    public void setStandardAnswer(String standardAnswer) {
        this.standardAnswer = standardAnswer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public List<QuestionItem> getItems() {
        return items;
    }

    public void setItems(List<QuestionItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", bankId=" + bankId +
                ", creator=" + creator +
                ", question='" + question + '\'' +
                ", remarks='" + remarks + '\'' +
                ", type=" + type +
                ", hasStandardAnswer=" + hasStandardAnswer +
                ", standardAnswer='" + standardAnswer + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", items=" + items +
                '}';
    }
}
