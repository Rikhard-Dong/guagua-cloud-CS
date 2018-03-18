package com.guagua.enums;

/**
 * @author ride
 * @date 18-3-14 下午2:28
 * <p>
 * 存放角色对应的常量
 */
public enum RoleConstant {
    BASE_USER(1),                       // 基本用户, 拥有基本的权限
    USER_ADMIN(2),                      // 用户管理员
    ROLE_ADMIN(3),                      // 角色管理员
    PERMISSION_ADMIN(4),                // 权限管理员
    AUDITOR(5),                         // 认证管理员
    UNCERTIFIED_ENTERPRISE(100),        // 未认证企业用户, 权限受限
    ENTERPRISE(101),                    // 企业用户
    UNCERTIFIED_MEMBER(200),            // 未认证会员用户, 权限受限
    MEMBER(201),                        // 会员用户
    ;

    private Integer code;

    RoleConstant() {
    }

    RoleConstant(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
