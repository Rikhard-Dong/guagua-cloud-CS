package com.guagua.bean.entity.admin;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-16 下午9:33
 * <p>
 * 处理会员用户的认证请求
 */
public class HandleMemberAuthentication {

    private Integer id;             // 主键
    private Integer applyId;        // 对应会员用户认证表id
    private Integer applyUserId;    // 申请人id, 对应用户表
    private Integer handleUserId;   // 处理人id, 对应用户表
    private Integer result;         // 处理结果, 0 申请认证同意,  1 申请认证不同意
    private String comment;         // 批注
    private Date handleTime;        // 处理时间

    public HandleMemberAuthentication() {
    }

    public HandleMemberAuthentication(Integer applyId, Integer applyUserId,
                                      Integer handleUserId, Integer result, String comment) {
        this.applyId = applyId;
        this.applyUserId = applyUserId;
        this.handleUserId = handleUserId;
        this.result = result;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Integer applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Integer getHandleUserId() {
        return handleUserId;
    }

    public void setHandleUserId(Integer handleUserId) {
        this.handleUserId = handleUserId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    @Override
    public String toString() {
        return "HandleMemberAuthentication{" +
                "id=" + id +
                ", applyId=" + applyId +
                ", applyUserId=" + applyUserId +
                ", handleUserId=" + handleUserId +
                ", result=" + result +
                ", comment='" + comment + '\'' +
                ", handleTime=" + handleTime +
                '}';
    }
}
