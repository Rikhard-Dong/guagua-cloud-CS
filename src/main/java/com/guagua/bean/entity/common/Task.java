package com.guagua.bean.entity.common;

import java.sql.Time;
import java.util.Date;

/**
 * @author ride
 * @date 18-3-23 下午3:31
 * <p>
 * 任务
 */
public class Task {

    private Integer id;             // id
    private Integer enterpriseId;   // 发布者(企业)id
    private String title;           // 任务标题
    private String description;     // 任务描述
    private Double baseSalary;      // 个人薪水
    private Double bonus;           // 个人奖金
    private Double totalSalary;     // 总花费 = (个人奖金+个人薪水) * 人数
    private Integer number;         // 人数
    private Date taskStartTime;         // 开始时间
    private Date taskEndTime;           // 结束时间
    private Date createTime;        // 创建时间
    private Time workingTimeStart1; // 工作开始时间段1
    private Time workingTimeEnd1;   // 工作结束时间段1
    private Time workingTimeStart2; // 工作开始时间段2
    private Time workingTimeEnd2;   // 工作结束时间段2
    private Time workingTimeStart3; // 工作开始时间段3
    private Time workingTimeEnd3;   // 工作结束时间段3
    private Time workingTimeStart4; // 工作开始时间段4
    private Time workingTimeEnd4;   // 工作结束时间段4
    private Boolean needUploadFile; // 是否需要上传相关文件
    private String uploadFilePath;  // 文件上传路径
    private Integer status;         // 任务状态, 参见任务状态字典

    public Task() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Time getWorkingTimeStart1() {
        return workingTimeStart1;
    }

    public void setWorkingTimeStart1(Time workingTimeStart1) {
        this.workingTimeStart1 = workingTimeStart1;
    }

    public Time getWorkingTimeEnd1() {
        return workingTimeEnd1;
    }

    public void setWorkingTimeEnd1(Time workingTimeEnd1) {
        this.workingTimeEnd1 = workingTimeEnd1;
    }

    public Time getWorkingTimeStart2() {
        return workingTimeStart2;
    }

    public void setWorkingTimeStart2(Time workingTimeStart2) {
        this.workingTimeStart2 = workingTimeStart2;
    }

    public Time getWorkingTimeEnd2() {
        return workingTimeEnd2;
    }

    public void setWorkingTimeEnd2(Time workingTimeEnd2) {
        this.workingTimeEnd2 = workingTimeEnd2;
    }

    public Time getWorkingTimeStart3() {
        return workingTimeStart3;
    }

    public void setWorkingTimeStart3(Time workingTimeStart3) {
        this.workingTimeStart3 = workingTimeStart3;
    }

    public Time getWorkingTimeEnd3() {
        return workingTimeEnd3;
    }

    public void setWorkingTimeEnd3(Time workingTimeEnd3) {
        this.workingTimeEnd3 = workingTimeEnd3;
    }

    public Time getWorkingTimeStart4() {
        return workingTimeStart4;
    }

    public void setWorkingTimeStart4(Time workingTimeStart4) {
        this.workingTimeStart4 = workingTimeStart4;
    }

    public Time getWorkingTimeEnd4() {
        return workingTimeEnd4;
    }

    public void setWorkingTimeEnd4(Time workingTimeEnd4) {
        this.workingTimeEnd4 = workingTimeEnd4;
    }

    public Boolean getNeedUploadFile() {
        return needUploadFile;
    }

    public void setNeedUploadFile(Boolean needUploadFile) {
        this.needUploadFile = needUploadFile;
    }

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", baseSalary=" + baseSalary +
                ", bonus=" + bonus +
                ", totalSalary=" + totalSalary +
                ", number=" + number +
                ", taskStartTime=" + taskStartTime +
                ", taskEndTime=" + taskEndTime +
                ", createTime=" + createTime +
                ", workingTimeStart1=" + workingTimeStart1 +
                ", workingTimeEnd1=" + workingTimeEnd1 +
                ", workingTimeStart2=" + workingTimeStart2 +
                ", workingTimeEnd2=" + workingTimeEnd2 +
                ", workingTimeStart3=" + workingTimeStart3 +
                ", workingTimeEnd3=" + workingTimeEnd3 +
                ", workingTimeStart4=" + workingTimeStart4 +
                ", workingTimeEnd4=" + workingTimeEnd4 +
                ", needUploadFile=" + needUploadFile +
                ", uploadFilePath='" + uploadFilePath + '\'' +
                ", status=" + status +
                '}';
    }
}
