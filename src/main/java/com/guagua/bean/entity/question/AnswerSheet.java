package com.guagua.bean.entity.question;

import java.util.Date;

/**
 * @author ride
 * @date 18-4-6 下午1:07
 * 答卷
 */
public class AnswerSheet {

    private Integer id;
    private Integer paperId;
    private Integer answerer;
    private Integer taskId;
    private Date createTime;
    private Integer score;
    private String remark;
    private Date markTime;
    private Integer evaluationStatus;

    public AnswerSheet() {
    }

    public AnswerSheet(Integer answerer, Integer taskId, Integer paperId) {
        this.paperId = paperId;
        this.answerer = answerer;
        this.taskId = taskId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getAnswerer() {
        return answerer;
    }

    public void setAnswerer(Integer answerer) {
        this.answerer = answerer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    public Date getMarkTime() {
        return markTime;
    }

    public void setMarkTime(Date markTime) {
        this.markTime = markTime;
    }

    public Integer getEvaluationStatus() {
        return evaluationStatus;
    }

    public void setEvaluationStatus(Integer evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }

    @Override
    public String toString() {
        return "AnswerSheet{" +
                "id=" + id +
                ", paperId=" + paperId +
                ", answerer=" + answerer +
                ", taskId=" + taskId +
                ", createTime=" + createTime +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                ", markTime=" + markTime +
                ", evaluationStatus=" + evaluationStatus +
                '}';
    }
}
