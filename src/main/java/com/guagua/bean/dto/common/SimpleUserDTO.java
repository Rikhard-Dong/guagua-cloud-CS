package com.guagua.bean.dto.common;

import com.guagua.bean.entity.common.User;
import com.guagua.utils.DateUtils;

/**
 * @author ride
 * @date 18-3-14 下午6:26
 */
public class SimpleUserDTO {

    private String phone;           // 用户手机号
    private String email;           // 邮箱
    private String username;        // 用户名
    private String headImage;       // 头像
    private String type;            // 用户类型
    private String sex;             // 用户性别
    private String createTime;      // 用户创建时间
    private String isDelete;        // 是否删除

    public SimpleUserDTO(User user) {
        this.username = user.getUsername();
        this.headImage = user.getHeadImage();
        this.phone = user.getPhone();
        this.createTime = DateUtils.date2StrCN(user.getCreateTime());
        switch (user.getType()) {
            case 0:
                this.type = "平台用户";
                break;
            case 1:
                this.type = "企业用户";
                break;
            case 2:
                this.type = "客服用户";
                break;
            default:
                this.type = "非法用户";
        }
        this.email = user.getEmail() == null ? "未绑定" : user.getEmail();
        switch (user.getSex()) {
            case 0:
                this.sex = "未知";
                break;
            case 1:
                this.sex = "男";
                break;
            case 2:
                this.sex = "女";
                break;
            default:
                this.sex = "异常";
        }
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "SimpleUserDTO{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", headImage='" + headImage + '\'' +
                ", type='" + type + '\'' +
                ", sex='" + sex + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isDelete='" + isDelete + '\'' +
                '}';
    }
}
