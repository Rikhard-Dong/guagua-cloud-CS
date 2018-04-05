package com.guagua.bean.dto.question;

import com.guagua.bean.entity.question.ExaminationPaper;
import com.guagua.bean.entity.question.Question;

import java.util.List;

/**
 * @author ride
 * @date 18-4-4 下午11:31
 */
public class ExaminationPaperDTO extends ExaminationPaperSimpleDTO {

    private List<Question> singleQuestions;
    private List<Question> multipleQuestions;
    private List<Question> judgementQuestions;
    private List<Question> textQuestions;

    public ExaminationPaperDTO() {
        super();
    }


    public ExaminationPaperDTO(ExaminationPaper paper) {
        super(paper);
    }

    public List<Question> getSingleQuestions() {
        return singleQuestions;
    }

    public void setSingleQuestions(List<Question> singleQuestions) {
        this.singleQuestions = singleQuestions;
    }

    public List<Question> getMultipleQuestions() {
        return multipleQuestions;
    }

    public void setMultipleQuestions(List<Question> multipleQuestions) {
        this.multipleQuestions = multipleQuestions;
    }

    public List<Question> getJudgementQuestions() {
        return judgementQuestions;
    }

    public void setJudgementQuestions(List<Question> judgementQuestions) {
        this.judgementQuestions = judgementQuestions;
    }

    public List<Question> getTextQuestions() {
        return textQuestions;
    }

    public void setTextQuestions(List<Question> textQuestions) {
        this.textQuestions = textQuestions;
    }

    @Override
    public String toString() {
        return "ExaminationPaperDTO{" +
                "singleQuestions=" + singleQuestions +
                ", multipleQuestions=" + multipleQuestions +
                ", judgementQuestions=" + judgementQuestions +
                ", textQuestions=" + textQuestions +
                ", id=" + id +
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
