package com.guagua.bean.dto.common;

import com.guagua.bean.entity.common.Role;
import com.guagua.bean.entity.common.User;
import com.guagua.utils.DateUtils;

import java.util.List;

/**
 * @author ride
 * @date 18-3-14 下午9:14
 * <p>
 * 用户详细信息dto
 */
public class UserDTO {
    private Integer userId;     // 用户id
    private String username;    // 用户名
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
    private String isDelete;                    // 用户是否被删除
    private List<Role> roles;                   // user have roles


    public UserDTO() {
    }

    public UserDTO(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.headImage = user.getHeadImage();
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
        this.email = user.getEmail() == null ? "未绑定邮箱" : user.getEmail();
        this.createTime = DateUtils.date2StrCN(user.getCreateTime());
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
        this.qq = user.getQq();
        this.wechat = user.getWechat();
        this.address = user.getAddress();
        this.description = user.getDescription();
        this.educationalExperience = user.getEducationalExperience();
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
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
                ", isDelete='" + isDelete + '\'' +
                ", roles=" + roles +
                '}';
    }
}
