package com.guagua.bean.entity.common;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-12 下午6:13
 * <p>
 * 手机号与验证码
 */
public class PhoneValidateCode {
    private Integer id;         // 主键自增
    private String phone;       // 手机号
    private String code;        // 验证码
    private Date sendTime;      // 发送时间

    public PhoneValidateCode() {
    }

    public PhoneValidateCode(String phone, String code) {
        this.phone = phone;
        this.code = code;
    }

    /**
     * 判断验证码是否过期
     *
     * @return true: 过期, false: 未过期
     */
    public boolean isExpire() {
        Date nowTime = new Date();
        Date expireTime = new Date(sendTime.getTime() + 5 * 60 * 1000);
        return nowTime.after(expireTime);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public String toString() {
        return "PhoneValidateCode{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
