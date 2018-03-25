package com.guagua.bean.dto.common;

import com.guagua.bean.entity.common.TaskApplication;
import com.guagua.utils.DateUtils;

/**
 * @author ride
 * @date 18-3-24 下午9:21
 */
public class TaskApplicationDTO {
    private Integer id;
    private Integer taskId;
    private String taskName;
    private Integer memberId;
    private String memberName;
    private Integer status;
    private String statusDesc;
    private String createTime;

    public TaskApplicationDTO() {
    }

    public TaskApplicationDTO(TaskApplication var1) {
        this.id = var1.getId();
        this.taskId = var1.getTaskId();
        this.memberId = var1.getMemberId();
        this.status = var1.getStatus();
        this.createTime = DateUtils.date2StrCN(var1.getCreateTime());

        switch (this.status) {
            case 0:
                this.statusDesc = "已投标";
                break;
            case 1:
                this.statusDesc = "竞标成功";
                break;
            case 2:
                this.statusDesc = "竞标失败";
                break;
            case 3:
                this.statusDesc = "超时未处理";
                break;
            case 4:
                this.statusDesc = "时间冲突";
                break;
            default:
                this.statusDesc = "状态错误";
        }
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TaskApplicationDTO{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", memberId=" + memberId +
                ", memberName=" + memberName +
                ", status=" + status +
                ", statusDesc='" + statusDesc + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
