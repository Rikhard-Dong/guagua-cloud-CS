package com.guagua.bean.entity.common;

import java.util.Date;

/**
 * @author ride
 * @date 18-4-1 下午7:19
 * <p>
 * 客户基础资料
 */
public class CustomerInfo {

    private String uuid;
    private String name;
    private Integer sex;    // 0 女 1 男
    private Integer age;
    private String phone;
    private String email;
    private Integer taskId;
    private Integer processorId;
    private Date createTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getProcessorId() {
        return processorId;
    }

    public void setProcessorId(Integer processorId) {
        this.processorId = processorId;
    }

    @Override
    public String toString() {
        return "CustomerInfo{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", taskId=" + taskId +
                ", processorId=" + processorId +
                ", createTime=" + createTime +
                '}';
    }
}
