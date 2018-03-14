package com.guagua.bean.dto.common;

import com.guagua.bean.entity.common.User;

/**
 * @author ride
 * @date 18-3-14 下午6:26
 */
public class SimpleUserDTO {

    private String username;        // 用户名
    private String headImage;       // 头像

    public SimpleUserDTO(User user) {
        this.username = user.getUsername();
        this.headImage = user.getHeadImage();
    }

    public SimpleUserDTO(String username, String headImage) {
        this.username = username;
        this.headImage = headImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    @Override
    public String toString() {
        return "SimpleUserDTO{" +
                "username='" + username + '\'' +
                ", headImage='" + headImage + '\'' +
                '}';
    }
}
