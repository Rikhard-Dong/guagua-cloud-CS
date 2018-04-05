package com.guagua.bean.entity.question;

import java.util.Date;

/**
 * @author ride
 * @date 18-4-4 下午7:42
 * <p>
 * 试卷
 */
public class ExaminationPaper {
    private Integer id;
    private Integer bankId;
    private Integer creator;
    private String name;
    private String description;
    private Integer type;       // 0 自动生成 1 手动身材隔行
    private Integer status;     // 0 不生效 1 生效 自动生成的试卷默认生效, 手动生成的 试卷需要绑定题目后才生效
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return "ExaminationPaper{" +
                "id=" + id +
                ", bankId=" + bankId +
                ", creator=" + creator +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", num=" + num +
                ", singleNum=" + singleNum +
                ", multipleNum=" + multipleNum +
                ", judgementNum=" + judgementNum +
                ", textNum=" + textNum +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getNumByType(Integer type) {
        Integer result;
        switch (type) {
            case 0:
                result = getSingleNum();
                break;
            case 1:
                result = getMultipleNum();
                break;
            case 2:
                result = getJudgementNum();
                break;
            case 3:
                result = getTextNum();
                break;
            default:
                result = -1;
        }

        return result;
    }
}
