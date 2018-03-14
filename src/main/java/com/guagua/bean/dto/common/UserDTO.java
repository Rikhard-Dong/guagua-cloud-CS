package com.guagua.bean.dto.common;

import com.guagua.bean.entity.common.User;
import com.guagua.utils.DateUtils;

/**
 * @author ride
 * @date 18-3-14 下午9:14
 * <p>
 * 用户详细信息dto
 */
public class UserDTO {
    private String username;        // 用户名
    private String phone;       // 用户手机号
    private String headImage;   // 用户头像
    private String type;        // 用户类型
    private String createTime;  // 用户创建时间


    public UserDTO() {
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.headImage = user.getHeadImage();
        switch (user.getType()) {
            case 0:
                type = "平台用户";
                break;
            case 1:
                type = "企业用户";
                break;
            case 2:
                type = "客服用户";
                break;
            default:
                type = "非法用户";
        }

        this.createTime = DateUtils.date2StrCN(user.getCreateTime());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", headImage='" + headImage + '\'' +
                ", type='" + type + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
