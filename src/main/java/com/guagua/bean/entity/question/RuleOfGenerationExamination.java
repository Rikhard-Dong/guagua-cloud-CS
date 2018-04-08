package com.guagua.bean.entity.question;

import java.util.Date;

/**
 * @author ride
 * @date 18-4-5 下午3:14
 * <p>
 * 自动生成试卷规则
 */
public class RuleOfGenerationExamination {
    private Integer id;
    private Integer bankId;
    private Integer creator;
    private String name;
    private String description;
    private Integer num;
    private Integer singleNum;
    private Integer multipleNum;
    private Integer judgementNum;
    private Integer textNum;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSingleNum() {
        return singleNum;
    }

    public void setSingleNum(Integer singleNum) {
        this.singleNum = singleNum;
    }

    public Integer getMultipleNum() {
        return multipleNum;
    }

    public void setMultipleNum(Integer multipleNum) {
        this.multipleNum = multipleNum;
    }

    public Integer getJudgementNum() {
        return judgementNum;
    }

    public void setJudgementNum(Integer judgementNum) {
        this.judgementNum = judgementNum;
    }

    public Integer getTextNum() {
        return textNum;
    }

    public void setTextNum(Integer textNum) {
        this.textNum = textNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "RuleOfGenerationExamination{" +
                "id=" + id +
                ", bankId=" + bankId +
                ", creator=" + creator +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", num=" + num +
                ", singleNum=" + singleNum +
                ", multipleNum=" + multipleNum +
                ", judgementNum=" + judgementNum +
                ", textNum=" + textNum +
                ", createTime=" + createTime +
                '}';
    }
}
