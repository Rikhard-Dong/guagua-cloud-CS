package com.guagua.bean.entity.common;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-24 下午9:14
 */
public class TaskApplication {
    private Integer id;
    private Integer taskId;
    private Integer publisherId;
    private Integer memberId;
    private Integer status;
    private Date createTime;

    public TaskApplication() {
    }

    public TaskApplication(Integer taskId, Integer publisherId, Integer memberId) {
        this.publisherId = publisherId;
        this.taskId = taskId;
        this.memberId = memberId;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
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

    @Override
    public String toString() {
        return "TaskApplication{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", publisherId=" + publisherId +
                ", memberId=" + memberId +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
