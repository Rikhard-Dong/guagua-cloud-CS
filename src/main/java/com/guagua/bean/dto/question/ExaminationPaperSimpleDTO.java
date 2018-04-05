package com.guagua.bean.dto.question;

import com.guagua.bean.entity.question.ExaminationPaper;
import com.guagua.utils.DateUtils;

/**
 * @author ride
 * @date 18-4-5 下午12:41
 */
public class ExaminationPaperSimpleDTO {
    protected Integer id;
    protected Integer bankId;
    protected String bankName;
    protected Integer creator;
    protected String creatorName;
    protected String name;
    protected String description;
    protected String type;
    protected String status;
    protected Integer num;
    protected Integer singleNum;
    protected Integer singleCurNum;
    protected Integer multipleNum;
    protected Integer multipleCurNum;
    protected Integer judgementNum;
    protected Integer judgementCurNum;
    protected Integer textNum;
    protected Integer textCurNum;
    protected String createTime;

    public ExaminationPaperSimpleDTO() {
    }


    public ExaminationPaperSimpleDTO(ExaminationPaper paper) {
        this.id = paper.getId();
        this.bankId = paper.getBankId();
        this.creator = paper.getCreator();
        this.name = paper.getName();
        this.description = paper.getDescription();
        this.type = (paper.getType() == 0 ? "自动生成" : "手动生成");
        this.status = (paper.getStatus() == 0 ? "试卷未生效" : "试卷已生效");
        this.num = paper.getNum();
        this.singleNum = paper.getSingleNum();
        this.multipleNum = paper.getMultipleNum();
        this.judgementNum = paper.getJudgementNum();
        this.textNum = paper.getTextNum();
        this.createTime = DateUtils.date2StrCN(paper.getCreateTime());
    }

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public Integer getSingleCurNum() {
        return singleCurNum;
    }

    public void setSingleCurNum(Integer singleCurNum) {
        this.singleCurNum = singleCurNum;
    }

    public Integer getMultipleNum() {
        return multipleNum;
    }

    public void setMultipleNum(Integer multipleNum) {
        this.multipleNum = multipleNum;
    }

    public Integer getMultipleCurNum() {
        return multipleCurNum;
    }

    public void setMultipleCurNum(Integer multipleCurNum) {
        this.multipleCurNum = multipleCurNum;
    }

    public Integer getJudgementNum() {
        return judgementNum;
    }

    public void setJudgementNum(Integer judgementNum) {
        this.judgementNum = judgementNum;
    }

    public Integer getJudgementCurNum() {
        return judgementCurNum;
    }

    public void setJudgementCurNum(Integer judgementCurNum) {
        this.judgementCurNum = judgementCurNum;
    }

    public Integer getTextNum() {
        return textNum;
    }

    public void setTextNum(Integer textNum) {
        this.textNum = textNum;
    }

    public Integer getTextCurNum() {
        return textCurNum;
    }

    public void setTextCurNum(Integer textCurNum) {
        this.textCurNum = textCurNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ExaminationPaperSimpleDTO{" +
                "id=" + id +
                ", bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                ", creator=" + creator +
                ", creatorName='" + creatorName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", num=" + num +
                ", singleNum=" + singleNum +
                ", singleCurNum=" + singleCurNum +
                ", multipleNum=" + multipleNum +
                ", multipleCurNum=" + multipleCurNum +
                ", judgementNum=" + judgementNum +
                ", judgementCurNum=" + judgementCurNum +
                ", textNum=" + textNum +
                ", textCurNum=" + textCurNum +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
