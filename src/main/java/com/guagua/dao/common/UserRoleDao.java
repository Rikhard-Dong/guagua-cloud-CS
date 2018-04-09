package com.guagua.dao.common;

import com.guagua.bean.entity.common.Role;
import com.guagua.bean.entity.common.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ride
 * @date 18-3-14 下午12:48
 * <p>
 * 用户权限表维护
 */
public interface UserRoleDao {
    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一套用户角色关联信息
     *
     * @param userRole user role 关联
     * @return result
     */
    Integer insertUserRole(UserRole userRole);

    /**
     * batch insert user role contact
     *
     * @param userRoles list of user role info
     * @return result
     */
    Integer insertBatch(List<UserRole> userRoles);

    /* ************************************************
     * delete
     *************************************************/

    /**
     * 根据id删除
     *
     * @param id id
     * @return id
     */
    Integer deleteById(Integer id);

    /**
     * delete by user id and role id
     *
     * @param userId user id
     * @param roleId role id
     * @return result
     */
    Integer deleteByUserAndRole(@Param("userId") Integer userId,
                                @Param("roleId") Integer roleId);

    /* ************************************************
     * select
     *************************************************/

    /**
     * 根据用户id查询该用户的所有角色
     *
     * @param userId user id
     * @return result
     */
    List<Role> findRolesByUserId(Integer userId);

    /**
     * 根据用户id 和 角色id查询关联记录
     *
     * @param userId user id
     * @param roleId role id
     * @return result
     */
    UserRole findByUserIdAndRoleId(@Param("userId") Integer userId,
                                   @Param("roleId") Integer roleId);


}
