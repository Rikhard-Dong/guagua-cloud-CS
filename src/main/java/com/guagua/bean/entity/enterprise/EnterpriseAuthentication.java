package com.guagua.bean.entity.enterprise;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-17 下午2:02
 * <p>
 * 企业的实名认证
 */
public class EnterpriseAuthentication {

    private Integer id;                     // 主键
    private Integer userId;                 // 申请用户id
    private String realName;                // 负责人真实姓名
    private String idNumber;                // 负责人证件编号
    private String enterpriseName;          // 公司名称
    private String businessLicense;         // 营业执照
    private String licensedResidence;       // 营业执照住所
    private String postalAddress;           // 通信地址
    private String legalPerson;             // 法人姓名
    private String officePhone;             // 办公室电话
    private String enterpriseDescription;   // 企业简介
    private String businessLicenseImg;      // 企业营业执照照片
    private String positiveImg;             // 负责人证件照正面
    private String negativeImg;             // 负责人证件照反面
    private Date applyTime;                 // 申请认证时间
    private Integer status;                 // 申请认证状态, 0 审核中, 1 审核通过 2 审核不通过

    public EnterpriseAuthentication() {
    }

    public EnterpriseAuthentication(Integer userId, String realName, String idNumber, String enterpriseName,
                                    String businessLicense, String licensedResidence, String postalAddress,
                                    String legalPerson, String officePhone, String enterpriseDescription,
                                    String businessLicenseImg, String positiveImg, String negativeImg) {
        this.userId = userId;
        this.realName = realName;
        this.idNumber = idNumber;
        this.enterpriseName = enterpriseName;
        this.businessLicense = businessLicense;
        this.licensedResidence = licensedResidence;
        this.postalAddress = postalAddress;
        this.legalPerson = legalPerson;
        this.officePhone = officePhone;
        this.enterpriseDescription = enterpriseDescription;
        this.businessLicenseImg = businessLicenseImg;
        this.positiveImg = positiveImg;
        this.negativeImg = negativeImg;
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

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getLicensedResidence() {
        return licensedResidence;
    }

    public void setLicensedResidence(String licensedResidence) {
        this.licensedResidence = licensedResidence;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getEnterpriseDescription() {
        return enterpriseDescription;
    }

    public void setEnterpriseDescription(String enterpriseDescription) {
        this.enterpriseDescription = enterpriseDescription;
    }

    public String getBusinessLicenseImg() {
        return businessLicenseImg;
    }

    public void setBusinessLicenseImg(String businessLicenseImg) {
        this.businessLicenseImg = businessLicenseImg;
    }

    public String getPositiveImg() {
        return positiveImg;
    }

    public void setPositiveImg(String positiveImg) {
        this.positiveImg = positiveImg;
    }

    public String getNegativeImg() {
        return negativeImg;
    }

    public void setNegativeImg(String negativeImg) {
        this.negativeImg = negativeImg;
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
        return "EnterpriseAuthentication{" +
                "id=" + id +
                ", userId=" + userId +
                ", realName='" + realName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", businessLicense='" + businessLicense + '\'' +
                ", licensedResidence='" + licensedResidence + '\'' +
                ", postalAddress='" + postalAddress + '\'' +
                ", legalPerson='" + legalPerson + '\'' +
                ", officePhone='" + officePhone + '\'' +
                ", enterpriseDescription='" + enterpriseDescription + '\'' +
                ", businessLicenseImg='" + businessLicenseImg + '\'' +
                ", positiveImg='" + positiveImg + '\'' +
                ", negativeImg='" + negativeImg + '\'' +
                ", applyTime=" + applyTime +
                ", status=" + status +
                '}';
    }
}
