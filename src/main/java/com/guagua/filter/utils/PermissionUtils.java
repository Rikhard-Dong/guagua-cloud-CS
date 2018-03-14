package com.guagua.filter.utils;

import com.guagua.bean.entity.common.Permission;
import com.guagua.bean.entity.common.Role;
import com.guagua.dao.common.RolePermissionDao;
import com.guagua.dao.common.UserRoleDao;
import com.guagua.utils.SpringContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.File;
import java.util.List;

/**
 * @author ride
 * @date 18-3-14 下午6:54
 * <p>
 * 权限检查类, 判断用户访问的url是否有权限
 */
public class PermissionUtils {

    private static final Logger logger = LoggerFactory.getLogger(PermissionUtils.class);

    private static UserRoleDao userRoleDao;

    private static RolePermissionDao rolePermissionDao;

    static {
        userRoleDao = (UserRoleDao) SpringContextUtils.getContext().getBean("userRoleDao");
        rolePermissionDao = (RolePermissionDao) SpringContextUtils.getContext().getBean("rolePermissionDao");
    }

    /**
     * 判断用户是否有该url权限
     *
     * @param userId        用户id
     * @param permissionUrl 权限url
     * @return true
     */
    public static boolean judge(Integer userId, String permissionUrl) {
        // 拿到用户当前的所有角色
        List<Role> roles = userRoleDao.findRolesByUserId(userId);
        logger.info("##### roles ====> {} ######", roles);
        for (Role role : roles) {
            List<Permission> permissions = rolePermissionDao.findAllPermissionByRoleId(role.getId());
            for (Permission permission : permissions) {
                // TODO 需要考虑到资源问题, 比如删除用户的url为delete:/manager/user/delete/*, *号代表可以匹配的
                if (StringUtils.equalsIgnoreCase(permissionUrl, permission.getUrl())) {
                    return true;
                }
            }
        }

        logger.info("##### 用户权限不足 #####");

        return false;
    }
}
