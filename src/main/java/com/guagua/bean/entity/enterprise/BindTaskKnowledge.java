package com.guagua.bean.entity.enterprise;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-29 下午7:15
 */
public class BindTaskKnowledge {

    private Integer id;
    private Integer taskId;
    private Integer knowledgeId;
    private Date bindDate;
    public BindTaskKnowledge() {
    }

    public BindTaskKnowledge(Integer taskId, Integer knowledgeId) {
        this.taskId = taskId;
        this.knowledgeId = knowledgeId;
    }

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

    public Integer getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Integer knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public Date getBindDate() {
        return bindDate;
    }

    public void setBindDate(Date bindDate) {
        this.bindDate = bindDate;
    }

    @Override
    public String toString() {
        return "BindTaskKnowledge{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", knowledgeId=" + knowledgeId +
                ", bindDate=" + bindDate +
                '}';
    }
}
