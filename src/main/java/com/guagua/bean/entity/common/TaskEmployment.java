package com.guagua.bean.entity.common;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-25 上午10:06
 * <p>
 * 任务雇佣表
 */
public class TaskEmployment {

    private Integer id;
    private Integer taskId;
    private Integer publisherId;    // 发布者id
    private Integer memberId;
    private Integer status;     // 0 任务未开始  1. 任务进行中 2. 任务已结束  3.任务取消
    private Date createTime;   // 建立雇佣时间
    private Float customerAvgScore;

    public TaskEmployment() {
    }

    public TaskEmployment(Integer taskId, Integer publisherId, Integer memberId) {
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

    public Float getCustomerAvgScore() {
        return customerAvgScore;
    }

    public void setCustomerAvgScore(Float customerAvgScore) {
        this.customerAvgScore = customerAvgScore;
    }

    @Override
    public String toString() {
        return "TaskEmployment{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", publisherId=" + publisherId +
                ", memberId=" + memberId +
                ", status=" + status +
                ", createTime=" + createTime +
                ", customerAvgScore=" + customerAvgScore +
                '}';
    }
}
