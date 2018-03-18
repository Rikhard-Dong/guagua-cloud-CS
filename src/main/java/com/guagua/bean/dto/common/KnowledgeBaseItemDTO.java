package com.guagua.bean.dto.common;

import com.guagua.bean.entity.common.KnowledgeBaseItem;
import com.guagua.utils.DateUtils;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-18 下午10:48
 * <p>
 * item dto
 */
public class KnowledgeBaseItemDTO {

    private Integer id;
    private Integer knowledgeBaseId;
    private String knowledgeBaseName;
    private String question;
    private String answer;
    private String similarQuestion1;
    private String similarQuestion2;
    private String similarQuestion3;
    private String similarQuestion4;
    private String similarQuestion5;
    private String createTime;


    public KnowledgeBaseItemDTO() {
    }

    public KnowledgeBaseItemDTO(KnowledgeBaseItem item) {
        this.id = item.getId();
        this.knowledgeBaseId = item.getKnowledgeBaseId();
        this.question = item.getQuestion();
        this.answer = item.getAnswer();
        this.similarQuestion1 = item.getSimilarQuestion1();
        this.similarQuestion2 = item.getSimilarQuestion2();
        this.similarQuestion3 = item.getSimilarQuestion3();
        this.similarQuestion4 = item.getSimilarQuestion4();
        this.similarQuestion5 = item.getSimilarQuestion5();
        this.createTime = DateUtils.date2StrCN(item.getCreateTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKnowledgeBaseId() {
        return knowledgeBaseId;
    }

    public void setKnowledgeBaseId(Integer knowledgeBaseId) {
        this.knowledgeBaseId = knowledgeBaseId;
    }

    public String getKnowledgeBaseName() {
        return knowledgeBaseName;
    }

    public void setKnowledgeBaseName(String knowledgeBaseName) {
        this.knowledgeBaseName = knowledgeBaseName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSimilarQuestion1() {
        return similarQuestion1;
    }

    public void setSimilarQuestion1(String similarQuestion1) {
        this.similarQuestion1 = similarQuestion1;
    }

    public String getSimilarQuestion2() {
        return similarQuestion2;
    }

    public void setSimilarQuestion2(String similarQuestion2) {
        this.similarQuestion2 = similarQuestion2;
    }

    public String getSimilarQuestion3() {
        return similarQuestion3;
    }

    public void setSimilarQuestion3(String similarQuestion3) {
        this.similarQuestion3 = similarQuestion3;
    }

    public String getSimilarQuestion4() {
        return similarQuestion4;
    }

    public void setSimilarQuestion4(String similarQuestion4) {
        this.similarQuestion4 = similarQuestion4;
    }

    public String getSimilarQuestion5() {
        return similarQuestion5;
    }

    public void setSimilarQuestion5(String similarQuestion5) {
        this.similarQuestion5 = similarQuestion5;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "KnowledgeBaseItemDTO{" +
                "id=" + id +
                ", knowledgeBaseId=" + knowledgeBaseId +
                ", knowledgeBaseName='" + knowledgeBaseName + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", similarQuestion1='" + similarQuestion1 + '\'' +
                ", similarQuestion2='" + similarQuestion2 + '\'' +
                ", similarQuestion3='" + similarQuestion3 + '\'' +
                ", similarQuestion4='" + similarQuestion4 + '\'' +
                ", similarQuestion5='" + similarQuestion5 + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
