package com.guagua.bean.dto.question;

import com.guagua.bean.entity.question.Question;
import com.guagua.bean.entity.question.QuestionItem;
import com.guagua.utils.DateUtils;

import java.util.List;

/**
 * @author ride
 * @date 18-4-4 下午4:16
 */
public class QuestionDTO {
    private Integer id;
    private Integer bankId;
    private String bankName;
    private String question;
    private String remarks;
    private String type;
    private String hasStandardAnswer;
    private List<Integer> answerIds;
    private String status;
    private List<QuestionItem> items;
    private String createTime;

    public QuestionDTO() {
    }

    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.bankId = question.getBankId();
        this.question = question.getQuestion();
        this.remarks = question.getRemarks();
        switch (question.getType()) {
            case 0:
                this.type = "单选题";
                break;
            case 1:
                this.type = "多选题";
                break;
            case 2:
                this.type = "判断题";
                break;
            case 3:
                this.type = "文字题";
                break;
            default:
                this.type = "错误";
                break;
        }

        switch (question.getStatus()) {
            case 0:
                this.status = "未启用";
                break;
            case 1:
                this.status = "启用";
                break;
            default:
                this.status = "异常";
                break;
        }

        switch (question.getHasStandardAnswer()) {
            case 0:
                this.hasStandardAnswer = "题目未启用";
                break;
            case 1:
                this.hasStandardAnswer = "题目已启用";
                break;
            default:
                this.hasStandardAnswer = "异常";
                break;
        }
        createTime = DateUtils.date2StrCN(question.getCreateTime());
    }

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHasStandardAnswer() {
        return hasStandardAnswer;
    }

    public void setHasStandardAnswer(String hasStandardAnswer) {
        this.hasStandardAnswer = hasStandardAnswer;
    }

    public List<Integer> getAnswerIds() {
        return answerIds;
    }

    public void setAnswerIds(List<Integer> answerIds) {
        this.answerIds = answerIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<QuestionItem> getItems() {
        return items;
    }

    public void setItems(List<QuestionItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id=" + id +
                ", bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                ", question='" + question + '\'' +
                ", remarks='" + remarks + '\'' +
                ", type='" + type + '\'' +
                ", hasStandardAnswer='" + hasStandardAnswer + '\'' +
                ", answerIds=" + answerIds +
                ", status='" + status + '\'' +
                ", items=" + items +
                '}';
    }
}
