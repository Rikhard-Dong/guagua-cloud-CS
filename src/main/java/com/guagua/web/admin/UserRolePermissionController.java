package com.guagua.web.admin;

import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.entity.common.Permission;
import com.guagua.bean.entity.common.Role;
import com.guagua.bean.entity.common.User;
import com.guagua.service.admin.UserRolePermissionService;
import com.guagua.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ride
 * @date 18-4-9 上午9:02
 * <p>
 * 用户角色权限管理
 */
@RestController
@RequestMapping("/admin")
public class UserRolePermissionController extends BaseController {

    private final UserRolePermissionService userRolePermissionService;

    @Autowired
    public UserRolePermissionController(UserRolePermissionService userRolePermissionService) {
        this.userRolePermissionService = userRolePermissionService;
    }

    /* *******************************
     * 用户管理模块
     *
     *********************************/

    /**
     * 后台管理员创建后台用户, 用户类型为0
     *
     * @param user    user
     * @param request request
     * @return result dto
     */
    @PostMapping("/user/background_user/create")
    public ResultDTO createUser(User user, HttpServletRequest request) {
        user.setType(0);
        return userRolePermissionService.createBackgroundUser(user);
    }

    /**
     * 查询所有后台用户 type0
     *
     * @param page    page
     * @param size    size
     * @param request request
     * @return result dto
     */
    @GetMapping("/user/background_user/list")
    public ResultDTO listBackGroundUser(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "size", defaultValue = "30") Integer size,
                                        HttpServletRequest request) {
        return userRolePermissionService.listUser(page, size, 0);
    }

    /**
     * 查询所有企业用户, type1
     *
     * @param page    page
     * @param size    size
     * @param request request
     * @return result dto
     */
    @GetMapping("/user/enterprise_user/list")
    public ResultDTO listEnterpriseUser(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "size", defaultValue = "30") Integer size,
                                        HttpServletRequest request) {
        return userRolePermissionService.listUser(page, size, 1);
    }

    /**
     * 查询所有客服用户 type2
     *
     * @param page    page
     * @param size    size
     * @param request request
     * @return result dto
     */
    @GetMapping("/user/member_user/list")
    public ResultDTO listMemberUser(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "30") Integer size,
                                    HttpServletRequest request) {
        return userRolePermissionService.listUser(page, size, 2);
    }

    /**
     * list user login info by user id
     *
     * @param userId user id
     * @param page   page
     * @param size   size
     * @return result dto include user login info
     */
    @GetMapping("/user/{userId}/login_info")
    public ResultDTO listUserLoginInfo(@PathVariable("userId") Integer userId,
                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "30") Integer size) {

        return userRolePermissionService.listUserLoginInfo(userId, page, size);
    }

    /**
     * get user detail info, include user has roles info
     *
     * @param userId user id
     * @return result dto
     */
    @GetMapping("/user/{userId}/detail")
    public ResultDTO userDetail(@PathVariable("userId") Integer userId) {
        return userRolePermissionService.userDetail(userId);
    }

    /**
     * delete user
     *
     * @param userId user id
     * @return result dto
     */
    @DeleteMapping("/user/{userId}/delete")
    public ResultDTO deleteUser(@PathVariable("userId") Integer userId) {
        return userRolePermissionService.deleteUser(userId);
    }

    /* *******************************
     * 角色管理模块
     *
     *********************************/

    /**
     * list all roles
     *
     * @param page page
     * @param size size
     * @return result dto
     */
    @GetMapping("/role/list")
    public ResultDTO listRoles(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "size", defaultValue = "30") Integer size) {
        return userRolePermissionService.listRoles(page, size);
    }

    /**
     * get role detail info, include permissions info
     *
     * @param roleId role id
     * @return result dto
     */
    @GetMapping("/role/{roleId}/detail")
    public ResultDTO listRoles(@PathVariable("roleId") Integer roleId) {
        return userRolePermissionService.roleDetail(roleId);
    }

    /**
     * create a new role
     *
     * @param role role
     * @return result dto
     */
    @PostMapping("/role/create")
    public ResultDTO createRole(Role role) {
        logger.info("role =====> {}", role);
        return userRolePermissionService.createRole(role);
    }

    /**
     * update role name and role description
     *
     * @param roleId role id
     * @param role   role
     * @return result dto
     */
    @PutMapping("/role/{roleId}/update")
    public ResultDTO updateRoleInfo(@PathVariable("roleId") Integer roleId,
                                    Role role) {
        role.setId(roleId);
        return userRolePermissionService.updateRoleInfo(role);
    }

    /**
     * delete role
     *
     * @param roleId role id
     * @return result dto
     */
    @DeleteMapping("/role/{roleId}/delete")
    public ResultDTO deleteRole(@PathVariable("roleId") Integer roleId) {
        return userRolePermissionService.deleteRole(roleId);
    }


    /* *******************************
     * 权限管理模块
     *
     *********************************/

    /**
     * list all permissions
     *
     * @param page page
     * @param size size
     * @return result dto
     */
    @GetMapping("/permission/list")
    public ResultDTO listPermissions(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", defaultValue = "30") Integer size) {
        return userRolePermissionService.listPermissions(page, size);
    }

    /**
     * create a new permission
     *
     * @param permission permission
     * @return result dto
     */
    @PostMapping("/permission/create")
    public ResultDTO createPermission(Permission permission) {
        return userRolePermissionService.createPermission(permission);
    }

    /**
     * update permission
     *
     * @param permissionId permission id
     * @param permission   permission
     * @return result dto
     */
    @PutMapping("/permission/{permissionId}/update")
    public ResultDTO updatePermission(@PathVariable("permissionId") Integer permissionId,
                                      Permission permission) {
        permission.setId(permissionId);
        return userRolePermissionService.updatePermission(permission);
    }

    /**
     * delete permission
     *
     * @param permissionId permission id
     * @return result dto
     */
    @DeleteMapping("/permission/{permissionId}/delete")
    public ResultDTO deletePermission(@PathVariable("permissionId") Integer permissionId) {
        return userRolePermissionService.deletePermission(permissionId);
    }

    /* *******************************
     * 用户角色管理模块
     *
     *********************************/

    /**
     * 建立用户角色联系
     *
     * @param userId  user id
     * @param roleIds role ids
     * @return result dto
     */
    @PostMapping("/user_role/create/contact")
    public ResultDTO createUserRoleContact(@RequestParam("userId") Integer userId,
                                           @RequestParam("roleIds[]") List<Integer> roleIds) {
        return userRolePermissionService.createUserRoleContact(userId, roleIds);
    }

    /**
     * 删除用户角色联系
     *
     * @param userId  user id
     * @param roleIds role ids
     * @return result dto
     */
    @DeleteMapping("/user_role/delete/contact")
    public ResultDTO deleteUserRoleContact(@RequestParam("userId") Integer userId,
                                           @RequestParam("roleIds[]") List<Integer> roleIds) {
        return userRolePermissionService.deleteUserRoleContact(userId, roleIds);
    }

    /* *******************************
     * 角色权限管理模块
     *
     *********************************/

    /**
     * 建立角色权限联系
     *
     * @param roleId        role id
     * @param permissionIds permission ids
     * @return result dto
     */
    @PostMapping("/role_permission/create/contact")
    public ResultDTO createRolePermissionContact(@RequestParam("roleId") Integer roleId,
                                                 @RequestParam("permissionIds[]") List<Integer> permissionIds) {
        return userRolePermissionService.createRolePermissionContact(roleId, permissionIds);
    }

    /**
     * 删除角色权限联系
     *
     * @param roleId        role id
     * @param permissionIds permission ids
     * @return result dto
     */
    @DeleteMapping("/role_permission/delete/contact")
    public ResultDTO deleteRolePermissionContact(@RequestParam("roleId") Integer roleId,
                                                 @RequestParam("permissionIds[]") List<Integer> permissionIds) {
        return userRolePermissionService.deleteRolePermissionContact(roleId, permissionIds);
    }

}
