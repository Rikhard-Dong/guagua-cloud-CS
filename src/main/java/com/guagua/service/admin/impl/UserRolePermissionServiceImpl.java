package com.guagua.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guagua.bean.dto.ResultDTO;
import com.guagua.bean.dto.common.SimpleUserDTO;
import com.guagua.bean.dto.common.UserDTO;
import com.guagua.bean.entity.MyPageInfo;
import com.guagua.bean.entity.common.*;
import com.guagua.dao.common.*;
import com.guagua.enums.DataDictionary;
import com.guagua.exception.common.CustomException;
import com.guagua.service.BaseService;
import com.guagua.service.admin.UserRolePermissionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ride
 * @date 18-4-9 上午9:05
 */
@Service("userRolePermissionServiceImpl")
@Transactional
public class UserRolePermissionServiceImpl extends BaseService implements UserRolePermissionService {

    private final UserDao userDao;

    private final RoleDao roleDao;

    private final PermissionDao permissionDao;

    private final UserRoleDao userRoleDao;

    private final RolePermissionDao rolePermissionDao;

    @Autowired
    public UserRolePermissionServiceImpl(UserDao userDao,
                                         RoleDao roleDao,
                                         PermissionDao permissionDao,
                                         UserRoleDao userRoleDao,
                                         RolePermissionDao rolePermissionDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.permissionDao = permissionDao;
        this.userRoleDao = userRoleDao;
        this.rolePermissionDao = rolePermissionDao;
    }



    /* *******************************
     * 用户管理模块
     *
     *********************************/

    // 创建后台管理用户
    public ResultDTO createBackgroundUser(User user) {
        user.encryptPassword(user.getPassword());
        Integer var1 = userDao.insertUser(user);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.INSERT_FAIL)
                    .addData("detail", "添加后台管理用户失败");
        }
        // 为后台用户绑定基础角色
        Integer var2 = userRoleDao.insertUserRole(new UserRole(user.getId(), 1));
        if (var2 == 0) {
            throw new CustomException(DataDictionary.INSERT_FAIL)
                    .addData("detail", "用户绑定角色错误");
        }
        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // 根据类型查询所有用户
    public ResultDTO listUser(Integer page, Integer size, Integer type) {
        PageHelper.startPage(page, size);
        List<User> users = userDao.findByType(type);
        PageInfo<User> info = new PageInfo<User>(users);
        MyPageInfo<SimpleUserDTO> myInfo = new MyPageInfo<SimpleUserDTO>(info);
        myInfo.setList(convert2SimpleUserDTO(info.getList()));

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("users", myInfo);
    }

    // get user detail info
    public ResultDTO userDetail(Integer userId) {
        User user = userDao.findById(userId);

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("user", convert2UserDTO(user));
    }

    // delete user
    public ResultDTO deleteUser(Integer userId) {
        Integer var1 = userDao.updateDeleteStatus(userId);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.DELETE_FAIL);
        }

        return new ResultDTO(DataDictionary.DELETE_SUCCESS);
    }




    /* *******************************
     * 角色管理模块
     *
     *********************************/

    // list all roles
    public ResultDTO listRoles(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Role> roles = roleDao.findAllRoles();
        PageInfo<Role> info = new PageInfo<Role>(roles);
        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("roles", info);
    }

    // get role detail info
    public ResultDTO roleDetail(Integer roleId) {
        Role role = roleDao.findById(roleId);
        if (role != null) {
            List<Permission> permissions = rolePermissionDao.findAllPermissionByRoleId(roleId);
            role.setPermissions(permissions);
        }

        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("role", role);
    }

    // create a new role
    public ResultDTO createRole(Role role) {
        Integer var1 = roleDao.insertRole(role);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.INSERT_FAIL);
        }
        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }


    // update role name and role description
    public ResultDTO updateRoleInfo(Role role) {
        Integer var1 = roleDao.updateRole(role);
        if (var1 == null) {
            throw new CustomException(DataDictionary.UPDATE_FAIL);
        }
        return new ResultDTO(DataDictionary.UPDATE_SUCCESS);
    }

    // delete role
    public ResultDTO deleteRole(Integer roleId) {
        Integer var1 = roleDao.deleteByRoleId(roleId);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.DELETE_FAIL);
        }
        return new ResultDTO(DataDictionary.DELETE_SUCCESS);
    }



    /* *******************************
     * 权限管理模块
     *
     *********************************/

    // list all permissions
    public ResultDTO listPermissions(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Permission> permissions = permissionDao.findAllPermissions();
        PageInfo<Permission> info = new PageInfo<Permission>(permissions);
        return new ResultDTO(DataDictionary.QUERY_SUCCESS).addData("permissions", info);
    }

    // create a new permission
    public ResultDTO createPermission(Permission permission) {
        Integer var1 = permissionDao.insertPermission(permission);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.INSERT_FAIL);
        }
        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // update permission
    public ResultDTO updatePermission(Permission permission) {
        Integer var1 = permissionDao.updatePermission(permission);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.UPDATE_FAIL);
        }
        return new ResultDTO(DataDictionary.UPDATE_SUCCESS);
    }

    // delete permission
    public ResultDTO deletePermission(Integer permissionId) {
        Integer var1 = permissionDao.deleteByPermissionId(permissionId);
        if (var1 == 0) {
            throw new CustomException(DataDictionary.DELETE_FAIL);
        }
        return new ResultDTO(DataDictionary.DELETE_SUCCESS);
    }






    /* *******************************
     * 用户角色管理模块
     *
     *********************************/

    // 建立用户角色的关联
    public ResultDTO createUserRoleContact(Integer userId, List<Integer> roleIds) {

        List<UserRole> userRoles = new ArrayList<UserRole>();
        for (Integer roleId : roleIds) {
            userRoles.add(new UserRole(userId, roleId));
        }
        Integer var1 = userRoleDao.insertBatch(userRoles);
        if (var1 != roleIds.size()) {
            throw new CustomException(DataDictionary.INSERT_FAIL);
        }

        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // 删除用户角色之间的关联
    public ResultDTO deleteUserRoleContact(Integer userId, List<Integer> roleIds) {

        for (Integer roleId : roleIds) {
            Integer var1 = userRoleDao.deleteByUserAndRole(userId, roleId);
            if (var1 == 0) {
                throw new CustomException(DataDictionary.DELETE_FAIL);
            }
        }

        return new ResultDTO(DataDictionary.DELETE_SUCCESS);
    }

    /* *******************************
     * 角色权限管理模块
     *
     *********************************/

    // 建立角色权限之间的关联
    public ResultDTO createRolePermissionContact(Integer roleId, List<Integer> permissionIds) {
        List<RolePermission> rolePermissions = new ArrayList<RolePermission>();
        for (Integer permissionId : permissionIds) {
            rolePermissions.add(new RolePermission(roleId, permissionId));
        }

        Integer var1 = rolePermissionDao.insertBatch(rolePermissions);
        if (var1 != permissionIds.size()) {
            throw new CustomException(DataDictionary.INSERT_FAIL);
        }
        return new ResultDTO(DataDictionary.INSERT_SUCCESS);
    }

    // 删除角色权限之间的关联
    public ResultDTO deleteRolePermissionContact(Integer roleId, List<Integer> permissionIds) {
        for (Integer permissionId : permissionIds) {
            Integer var1 = rolePermissionDao.deleteByRoleAndPermission(roleId, permissionId);
            if (var1 == 0) {
                throw new CustomException(DataDictionary.DELETE_FAIL);
            }
        }

        return new ResultDTO(DataDictionary.DELETE_SUCCESS);
    }

    /* *******************************
     * private function
     *
     *********************************/

    /**
     * convert to simple user dto
     *
     * @param users user list
     * @return simple user dto list
     */
    private List<SimpleUserDTO> convert2SimpleUserDTO(List<User> users) {
        if (users == null || users.size() == 0) {
            return null;
        }
        List<SimpleUserDTO> result = new ArrayList<SimpleUserDTO>();

        for (User user : users) {
            SimpleUserDTO simpleUserDTO = new SimpleUserDTO(user);
            simpleUserDTO.setIsDelete(user.getIsDelete() == 0 ? "正常" : "已删除");
            result.add(simpleUserDTO);
        }

        return result;
    }

    /**
     * convert to user dto
     *
     * @param user user
     * @return user DTO
     */
    private UserDTO convert2UserDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO result = new UserDTO(user);
        result.setIsDelete(user.getIsDelete() == 0 ? "正常" : "已删除");
        List<Role> roles = userRoleDao.findRolesByUserId(user.getId());
        result.setRoles(roles);

        return result;
    }
}
