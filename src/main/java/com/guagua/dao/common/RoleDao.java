package com.guagua.dao.common;

import com.guagua.bean.entity.common.Role;

import java.util.List;

/**
 * @author ride
 * @date 18-3-14 下午12:29
 */
public interface RoleDao {
    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一个角色
     *
     * @param role 带插入的角色
     * @return 执行结果
     */
    Integer insertRole(Role role);

    /* ************************************************
     * update
     *************************************************/

    /**
     * update role
     *
     * @param role
     * @return
     */
    Integer updateRole(Role role);

    /* ************************************************
     * delete
     *************************************************/

    /**
     * delete role by role id
     *
     * @param roleId role id
     * @return result
     */
    Integer deleteByRoleId(Integer roleId);

    /* ************************************************
     * select
     *************************************************/

    /**
     * 查询所有的角色
     *
     * @return 角色
     */
    List<Role> findAllRoles();

    /**
     * find role by role id
     *
     * @param roleId role id
     * @return role
     */
    Role findById(Integer roleId);
}
