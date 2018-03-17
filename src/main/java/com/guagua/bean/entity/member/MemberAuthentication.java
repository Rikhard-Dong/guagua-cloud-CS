package com.guagua.bean.entity.member;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-16 下午4:32
 * <p>
 * 用户的实名认证信息
 */
public class MemberAuthentication {
    private Integer id;                     // 主键id
    private Integer userId;                 // 外键, 对应用户表id
    private String realName;                // 真实姓名
    private String idNumber;                // 身份证号
    private String certificatesPositive;    //  身份证正面照
    private String certificatesNegative;    // 身份照反面照
    private Date applyTime;                 // 申请时间
    private Integer status;                 // 申请结果 0 申请中, 1. 申请成功, 2. 申请失败

    public MemberAuthentication() {
    }

    public MemberAuthentication(Integer userId, String realName, String idNumber,
                                String certificatesPositive, String certificatesNegative) {
        this.userId = userId;
        this.realName = realName;
        this.idNumber = idNumber;
        this.certificatesPositive = certificatesPositive;
        this.certificatesNegative = certificatesNegative;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCertificatesPositive() {
        return certificatesPositive;
    }

    public void setCertificatesPositive(String certificatesPositive) {
        this.certificatesPositive = certificatesPositive;
    }

    public String getCertificatesNegative() {
        return certificatesNegative;
    }

    public void setCertificatesNegative(String certificatesNegative) {
        this.certificatesNegative = certificatesNegative;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MemberAuthentication{" +
                "id=" + id +
                ", userId=" + userId +
                ", realName='" + realName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", certificatesPositive='" + certificatesPositive + '\'' +
                ", certificatesNegative='" + certificatesNegative + '\'' +
                ", applyTime=" + applyTime +
                ", status=" + status +
                '}';
    }
}
