package com.guagua.bean.dto.common;

import com.guagua.bean.entity.common.Task;
import com.guagua.utils.DateUtils;

/**
 * @author ride
 * @date 18-3-23 下午9:07
 */
public class TaskDto {
    private Integer taskId;
    private Integer enterpriseId;
    private String enterpriseName;
    private String title;
    private String description;
    private Double baseSalary;
    private Double bonus;
    private Double totalSalary;
    private String taskStartTime;
    private String taskEndTime;
    private Integer type;
    private String typeDesc;
    private Integer number;
    private String createTime;
    private Integer status;
    private String statusDesc;

    public TaskDto() {
    }

    public TaskDto(Task task) {
        this.taskId = task.getId();
        this.enterpriseId = task.getEnterpriseId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.baseSalary = task.getBaseSalary();
        this.bonus = task.getBonus();
        this.totalSalary = task.getTotalSalary();
        this.taskStartTime = DateUtils.date2StrCN(task.getTaskStartTime());
        this.taskEndTime = DateUtils.date2StrCN(task.getTaskEndTime());
        this.createTime = DateUtils.date2StrCN(task.getCreateTime());
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
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

    public String getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(String taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public String getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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


    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }
}
