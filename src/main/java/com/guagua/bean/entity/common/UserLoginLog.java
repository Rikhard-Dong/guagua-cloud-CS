package com.guagua.bean.entity.common;

import java.util.Date;

/**
 * @author ride
 * @date 18-3-13 下午9:24
 * <p>
 * 用户登录日志
 */
public class UserLoginLog {

    private Integer id;         // 主键
    private Integer userId;     // 用户id
    private String loginIp;     // 登录ip
    private Date loginDate;     // 登录时间

    public UserLoginLog() {
    }

    public UserLoginLog(Integer userId, String loginIp) {
        this.userId = userId;
        this.loginIp = loginIp;
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

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public String toString() {
        return "UserLoginLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", loginIp='" + loginIp + '\'' +
                ", loginDate=" + loginDate +
                '}';
    }
}
