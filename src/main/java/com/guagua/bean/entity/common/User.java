package com.guagua.bean.entity.common;

import com.guagua.utils.CryptographyUtils;
import com.guagua.utils.UUIDUtils;

import java.util.Date;
import java.util.List;

public class User {
    private Integer id;             // 用户id, 自增长
    private String phone;           // 手机号
    private String username;        // 用户名
    private String password;        // 密码, 盐值加密
    private String salt;            // 盐值加密, 规则: 创建时间加上随机uuid, 32为长度
    private Date createTime;        // 用户创建时间
    private String headImage;       // 用户头像
    private String email;           // 用户邮箱
    private Integer type;           // 用户类型: 主要有三类: 0. 平台用户 1. 企业用户 2. 客服用户
    private Integer sex;            // 用户性别
    private String qq;              // qq号
    private String wechat;          // 微信号
    private String address;         // 地址
    private String description;     // 个人简介
    private String educationalExperience;   // 教育经历

    private List<Role> roles;       // 该用户拥有的角色

    /**
     * 无参构造函数
     */
    public User() {
    }

    /**
     * 注册构造函数
     *
     * @param phone    手机号
     * @param password 密码
     * @param type     类型
     */
    public User(String phone, String password, Integer type) {
        this.phone = phone;
        this.type = type;
        this.createTime = new Date();
        this.salt = createTime.getTime() + UUIDUtils.getUUID();
        this.salt = salt.substring(0, 32);
        this.password = CryptographyUtils.md5(password, salt);
    }

    /**
     * 注册用构造函数2
     *
     * @param phone    用户手机号
     * @param username 用户名
     * @param password 未加密密码
     * @param type     用户类型
     */
    public User(String phone, String username, String password, Integer type) {
        this.phone = phone;
        this.username = username;
        this.createTime = new Date();
        this.type = type;
        this.salt = createTime.getTime() + UUIDUtils.getUUID();
        this.salt = salt.substring(0, 32);
        this.password = CryptographyUtils.md5(password, salt);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
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
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", createTime=" + createTime +
                ", headImage='" + headImage + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                ", sex=" + sex +
                ", qq='" + qq + '\'' +
                ", wechat='" + wechat + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", educationalExperience='" + educationalExperience + '\'' +
                ", roles=" + roles +
                '}';
    }
}
