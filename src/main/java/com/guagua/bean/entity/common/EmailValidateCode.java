package com.guagua.bean.entity.common;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-15 下午3:05
 * <p>
 * 邮箱验证码 三十分钟内可以使用
 */
public class EmailValidateCode {

    private Integer id;     // id
    private String email;   // 邮箱
    private String code;    // 验证码
    private Date sendTime;  // 发送时间
    private Integer userId; // 外键 对应用户id

    public EmailValidateCode() {
    }

    public EmailValidateCode(Integer userId, String email, String code) {
        this.userId = userId;
        this.email = email;
        this.code = code;
    }

    /**
     * 邮箱验证码15分钟内有效
     *
     * @return true 过期, false 没过期
     */
    public boolean isExpire() {
        Date nowTime = new Date();
        Date expireTime = new Date(sendTime.getTime() + 15 * 60 * 1000);
        return nowTime.after(expireTime);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "EmailValidateCode{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", sendTime=" + sendTime +
                ", userId=" + userId +
                '}';
    }
}
