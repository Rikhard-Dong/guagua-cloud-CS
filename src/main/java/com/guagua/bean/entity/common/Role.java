package com.guagua.bean.entity.common;

/**
 * @author ride
 * @date 18-3-14 上午11:02
 * <p>
 * 角色bean
 */
public class Role {

    private Integer id;             // 主键
    private String roleName;        // 角色名称
    private String description;     // 角色描述

    public Role() {

    }

    public Role(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
