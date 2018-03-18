package com.guagua.utils;


import com.guagua.bean.entity.common.Role;
import com.guagua.dao.common.UserRoleDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author ride
 * @date 18-3-17 下午10:32
 * <p>
 * 判断用户是否有权限
 */
public class RoleUtils {
    private static UserRoleDao userRoleDao;

    static {
        userRoleDao = (UserRoleDao) SpringContextUtils.getContext().getBean("userRoleDao");
    }

    /**
     * 判断用户是否有该角色
     *
     * @param userId   用户id
     * @param roleName 角色名
     * @return true: 拥有 false: 没有权限
     */
    public static boolean hasRole(Integer userId, String roleName) {
        List<Role> roles = userRoleDao.findRolesByUserId(userId);
        for (Role role : roles) {
            if (StringUtils.equalsIgnoreCase(roleName, role.getRoleName())) {
                return true;
            }
        }

        return false;
    }


}
