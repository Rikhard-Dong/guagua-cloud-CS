package com.guagua.bean.dto.common;

import com.guagua.bean.entity.common.TaskApplication;
import com.guagua.bean.entity.common.User;
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

    private String sex;
    private String phone;
    private String qq;
    private String wechat;
    private String address;
    private String description;
    private String educationalExperience;
    private String email;


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

    public void setMemberInfo(User member) {
        this.memberName = member.getUsername();
        switch (member.getSex()) {
            case 0:
                this.sex = "未知";
                break;
            case 1:
                this.sex = "女";
                break;
            case 2:
                this.sex = "男";
                break;
        }
        this.qq = member.getQq();
        this.wechat = member.getWechat();
        this.phone = member.getPhone();
        this.email = member.getEmail();
        this.description = member.getDescription();
        this.address = member.getAddress();
        this.educationalExperience = member.getEducationalExperience();
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEducationalExperience() {
        return educationalExperience;
    }

    public void setEducationalExperience(String educationalExperience) {
        this.educationalExperience = educationalExperience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "TaskApplicationDTO{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", status=" + status +
                ", statusDesc='" + statusDesc + '\'' +
                ", createTime='" + createTime + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", wechat='" + wechat + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", educationalExperience='" + educationalExperience + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
