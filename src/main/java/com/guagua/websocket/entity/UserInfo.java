package com.guagua.websocket.entity;

import java.io.Serializable;

/**
 * @author ride
 * @date 18-3-30 下午6:27
 */
public class UserInfo implements Serializable {


    public final static Integer USER_TYPE_MEMBER = 0;
    public final static Integer USER_TYPE_ANON = 1;
    public final static Integer USER_TYPE_OTHER = 2;

    private String userId;   // 针对0和2用户
    private Integer userType; // 0 客服用户, 1 匿名用户 2 其他用户(包括企业和后台管理人员)
    private String username;  // 用户名, 如果是站内用户使用站内用户名, 如果是飞站内用户名, 则需要用户输入

    // 匿名用户需要携带任务信息
    private Integer taskId;

    public UserInfo() {

    }

    public UserInfo(String userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", userType=" + userType +
                ", username='" + username + '\'' +
                ", taskId=" + taskId +
                '}';
    }
}
