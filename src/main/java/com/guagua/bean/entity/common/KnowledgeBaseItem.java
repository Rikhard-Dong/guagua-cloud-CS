package com.guagua.bean.entity.common;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-18 下午5:53
 * <p>
 * 知识库具体条目
 */
public class KnowledgeBaseItem {
    private Integer id;
    private Integer knowledgeBaseId;
    private String question;
    private String answer;
    private String similarQuestion1;
    private String similarQuestion2;
    private String similarQuestion3;
    private String similarQuestion4;
    private String similarQuestion5;
    private Date createTime;

    public KnowledgeBaseItem() {
    }

    public KnowledgeBaseItem(Integer knowlegdeBaseId, String question, String answer) {
        this.knowledgeBaseId = knowlegdeBaseId;
        this.question = question;
        this.answer = answer;
    }

    public KnowledgeBaseItem(Integer knowlegdeBaseId, String question, String answer, String similarQuestion1) {
        this.knowledgeBaseId = knowlegdeBaseId;
        this.question = question;
        this.answer = answer;
        this.similarQuestion1 = similarQuestion1;
    }

    public KnowledgeBaseItem(Integer knowlegdeBaseId, String question, String answer,
                             String similarQuestion1, String similarQuestion2) {
        this.knowledgeBaseId = knowlegdeBaseId;
        this.question = question;
        this.answer = answer;
        this.similarQuestion1 = similarQuestion1;
        this.similarQuestion2 = similarQuestion2;
    }

    public KnowledgeBaseItem(Integer knowlegdeBaseId, String question, String answer, String similarQuestion1,
                             String similarQuestion2, String similarQuestion3) {
        this.knowledgeBaseId = knowlegdeBaseId;
        this.question = question;
        this.answer = answer;
        this.similarQuestion1 = similarQuestion1;
        this.similarQuestion2 = similarQuestion2;
        this.similarQuestion3 = similarQuestion3;
    }

    public KnowledgeBaseItem(Integer knowlegdeBaseId, String question, String answer,
                             String similarQuestion1, String similarQuestion2,
                             String similarQuestion3, String similarQuestion4) {
        this.knowledgeBaseId = knowlegdeBaseId;
        this.question = question;
        this.answer = answer;
        this.similarQuestion1 = similarQuestion1;
        this.similarQuestion2 = similarQuestion2;
        this.similarQuestion3 = similarQuestion3;
        this.similarQuestion4 = similarQuestion4;
    }

    public KnowledgeBaseItem(Integer knowlegdeBaseId, String question, String answer, String similarQuestion1,
                             String similarQuestion2, String similarQuestion3,
                             String similarQuestion4, String similarQuestion5) {
        this.knowledgeBaseId = knowlegdeBaseId;
        this.question = question;
        this.answer = answer;
        this.similarQuestion1 = similarQuestion1;
        this.similarQuestion2 = similarQuestion2;
        this.similarQuestion3 = similarQuestion3;
        this.similarQuestion4 = similarQuestion4;
        this.similarQuestion5 = similarQuestion5;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "KnowledgeBaseItem{" +
                "id=" + id +
                ", knowledgeBaseId=" + knowledgeBaseId +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", similarQuestion1='" + similarQuestion1 + '\'' +
                ", similarQuestion2='" + similarQuestion2 + '\'' +
                ", similarQuestion3='" + similarQuestion3 + '\'' +
                ", similarQuestion4='" + similarQuestion4 + '\'' +
                ", similarQuestion5='" + similarQuestion5 + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
