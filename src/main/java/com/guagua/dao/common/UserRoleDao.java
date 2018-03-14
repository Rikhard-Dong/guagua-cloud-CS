package com.guagua.dao.common;

import com.guagua.bean.entity.common.Role;
import com.guagua.bean.entity.common.UserRole;

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
    Integer insertUserRole(UserRole userRole);


    /* ************************************************
     * select
     *************************************************/
    List<Role> findRolesByUserId(Integer userId);
}
