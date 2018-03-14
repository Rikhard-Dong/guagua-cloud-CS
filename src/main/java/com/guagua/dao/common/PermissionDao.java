package com.guagua.dao.common;

import com.guagua.bean.entity.common.Permission;
import org.apache.ibatis.annotations.Param;

/**
 * @author ride
 * @date 18-3-14 下午3:02
 */
public interface PermissionDao {

    /* ************************************************
     * insert
     *************************************************/

    /**
     * 插入一条权限信息
     *
     * @param permission 权限
     * @return 执行结果
     */
    Integer insertPermission(@Param("permission") Permission permission);
}
