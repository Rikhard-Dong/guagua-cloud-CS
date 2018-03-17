package com.guagua.bean.dto.member;

import com.guagua.bean.entity.member.MemberAuthentication;
import com.guagua.utils.DateUtils;

/**
 * @author ride
 * @date 18-3-16 下午7:51
 */
public class MemberAuthenticationDTO {
    private Integer id;             // 对应审核id
    private Integer userId;          // 用户id
    private String username;        // 用户名
    private String realName;        // 真实姓名
    private String idNumber;        // 身份证号
    private String positiveUrl;     // 正面照url
    private String negativeUrl;     // 反面照url
    private String applyTime;       // 提交申请时间
    private String status;          // 状态

    public MemberAuthenticationDTO() {
    }

    public MemberAuthenticationDTO(MemberAuthentication authentication) {
        this.id = authentication.getId();
        this.realName = authentication.getRealName();
        this.idNumber = authentication.getIdNumber();
        this.positiveUrl = authentication.getCertificatesPositive();
        this.negativeUrl = authentication.getCertificatesNegative();
        switch (authentication.getStatus()) {
            case 0:
                this.status = "审核中";
                break;
            case 1:
                this.status = "审核通过";
                break;
            case 2:
                this.status = "审核失败";
                break;
            default:
                this.status = "错误状态";
        }
        this.userId = authentication.getUserId();
        this.applyTime = DateUtils.date2StrCN(authentication.getApplyTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPositiveUrl() {
        return positiveUrl;
    }

    public void setPositiveUrl(String positiveUrl) {
        this.positiveUrl = positiveUrl;
    }

    public String getNegativeUrl() {
        return negativeUrl;
    }

    public void setNegativeUrl(String negativeUrl) {
        this.negativeUrl = negativeUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }


    @Override
    public String toString() {
        return "MemberAuthenticationDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", positiveUrl='" + positiveUrl + '\'' +
                ", negativeUrl='" + negativeUrl + '\'' +
                ", applyTime='" + applyTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
