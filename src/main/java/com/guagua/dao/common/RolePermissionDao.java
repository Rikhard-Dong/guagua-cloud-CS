package com.guagua.dao.common;

import com.guagua.bean.entity.common.Permission;
import com.guagua.bean.entity.common.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-3-14 下午3:02
 */
public interface RolePermissionDao {

    /* ************************************************
     * insert
     *************************************************/

    /**
     * 建立角色与权限的联系
     *
     * @param rolePermission 角色权限
     * @return 执行结果
     */
    Integer insertRolePermission(RolePermission rolePermission);

    /**
     * batch crate role permission contact
     *
     * @param rolePermissions role permission
     * @return result dto
     */
    Integer insertBatch(List<RolePermission> rolePermissions);


    /* ************************************************
     * delete
     *************************************************/

    /**
     * delete by role id and permission id
     *
     * @param roleId       role id
     * @param permissionId permission id
     * @return result
     */
    Integer deleteByRoleAndPermission(@Param("roleId") Integer roleId,
                                      @Param("permissionId") Integer permissionId);

    /* ************************************************
     * select
     *************************************************/


    /**
     * 查询该角色的所有权限
     *
     * @param roleId 用户权限
     * @return 权限集合
     */
    List<Permission> findAllPermissionByRoleId(Integer roleId);


}
