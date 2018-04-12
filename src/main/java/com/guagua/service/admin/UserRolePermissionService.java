package com.guagua.service.admin;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.common.Permission;
import com.guagua.bean.entity.common.Role;
import com.guagua.bean.entity.common.User;

import java.util.List;

/**
 * @author ride
 * @date 18-4-9 上午9:05
 */

public interface UserRolePermissionService {
    /**
     * 添加一个后台管理用户
     *
     * @param user user
     * @return result dto
     */
    ResultDTO createBackgroundUser(User user);

    /**
     * 根据用户类型列出所有用户
     *
     * @param page page
     * @param size size
     * @param type type
     * @return result dto
     */
    ResultDTO listUser(Integer page, Integer size, Integer type);

    /**
     * list all roles
     *
     * @param page page
     * @param size size
     * @return result dto
     */
    ResultDTO listRoles(Integer page, Integer size);

    /**
     * list all permissions
     *
     * @param page page
     * @param size size
     * @return result dto
     */
    ResultDTO listPermissions(Integer page, Integer size);

    /**
     * user detail info
     *
     * @param userId user id
     * @return result dto
     */
    ResultDTO userDetail(Integer userId);

    /**
     * get role detail info
     *
     * @param roleId role id
     * @return result dto
     */
    ResultDTO roleDetail(Integer roleId);

    /**
     * delete user, just update user isDelete column 1
     *
     * @param userId user id
     * @return result dto
     */
    ResultDTO deleteUser(Integer userId);

    /**
     * update role name and role description
     *
     * @param role role
     * @return result dto
     */
    ResultDTO updateRoleInfo(Role role);

    /**
     * create a new role
     *
     * @param role role
     * @return result dto
     */
    ResultDTO createRole(Role role);

    /**
     * delete role
     *
     * @param roleId role id
     * @return result dto
     */
    ResultDTO deleteRole(Integer roleId);

    /**
     * create a new permission
     *
     * @param permission permission
     * @return result dto
     */
    ResultDTO createPermission(Permission permission);

    /**
     * update permission
     *
     * @param permission permission
     * @return result dto
     */
    ResultDTO updatePermission(Permission permission);

    /**
     * delete  permission
     *
     * @param permissionId permission id
     * @return result dto
     */
    ResultDTO deletePermission(Integer permissionId);

    /**
     * 建立用户角色之间的联系
     *
     * @param userId  user id
     * @param roleIds role ids
     * @return result dto
     */
    ResultDTO createUserRoleContact(Integer userId, List<Integer> roleIds);

    /**
     * 删除用户角色之间的联系
     *
     * @param userId  user id
     * @param roleIds role ids
     * @return result dto
     */
    ResultDTO deleteUserRoleContact(Integer userId, List<Integer> roleIds);

    /**
     * 建立角色权限之间的联系
     *
     * @param roleId        role id
     * @param permissionIds permission ids
     * @return result dto
     */
    ResultDTO createRolePermissionContact(Integer roleId, List<Integer> permissionIds);

    /**
     * 删除用户权限之间的联系
     *
     * @param roleId        role id
     * @param permissionIds permission ids
     * @return result dto
     */
    ResultDTO deleteRolePermissionContact(Integer roleId, List<Integer> permissionIds);

    /**
     * 用户登录日志信息
     *
     * @param userId user id
     * @param page   page
     * @param size   size
     * @return result dto
     */
    ResultDTO listUserLoginInfo(Integer userId, Integer page, Integer size);
}
