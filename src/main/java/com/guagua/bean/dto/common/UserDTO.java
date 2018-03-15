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
    private String email;       // 用户邮箱
    private String createTime;  // 用户创建时间
    private String sex;         // 性别, 0代表男性
    private String qq;          // qq
    private String wechat;      // 微信
    private String address;     // 地址
    private String description; // 描述
    private String educationalExperience;       // 教育经历


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
        this.email = user.getEmail() == null ? "未绑定邮箱" : user.getEmail();
        this.createTime = DateUtils.date2StrCN(user.getCreateTime());
        this.sex = user.getSex() == 1 ? "男" : "女";
        this.qq = user.getQq();
        this.wechat = user.getWechat();
        this.address = user.getAddress();
        this.description = user.getDescription();
        this.educationalExperience = user.getEducationalExperience();
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

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEducationalExperience() {
        return educationalExperience;
    }

    public void setEducationalExperience(String educationalExperience) {
        this.educationalExperience = educationalExperience;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", headImage='" + headImage + '\'' +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                ", createTime='" + createTime + '\'' +
                ", sex='" + sex + '\'' +
                ", qq='" + qq + '\'' +
                ", wechat='" + wechat + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", educationalExperience='" + educationalExperience + '\'' +
                '}';
    }
}
