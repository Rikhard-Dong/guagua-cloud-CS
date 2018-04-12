package com.guagua.bean.entity.member;

import java.util.Date;

/**
 * @author ride
 * @date 18-4-11 下午3:09
 * 普通用户对客服进行评分
 */
public class SatisfactionEvaluation {
    private Integer id;
    private Integer taskId;
    private Integer memberId;
    private Integer score;
    private String content;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SatisfactionEvaluation{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", memberId=" + memberId +
                ", score=" + score +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
