package com.guagua.bean.entity.question;

import java.util.Date;

/**
 * @author ride
 * @date 18-4-4 上午10:44
 */
public class QuestionBank {
    private Integer id;
    private String bankName;
    private String description;
    private Integer creator;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "QuestionBank{" +
                "id=" + id +
                ", bankName='" + bankName + '\'' +
                ", description='" + description + '\'' +
                ", creator=" + creator +
                ", createTime=" + createTime +
                '}';
    }
}
