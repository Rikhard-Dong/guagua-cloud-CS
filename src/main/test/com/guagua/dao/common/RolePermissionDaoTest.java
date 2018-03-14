package com.guagua.dao.common;

import com.guagua.bean.entity.common.Permission;
import com.guagua.bean.entity.common.RolePermission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-14 下午6:24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class RolePermissionDaoTest {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Test
    public void insertRolePermission() {
        RolePermission rolePermission = new RolePermission(1, 1);
        Integer var1 = rolePermissionDao.insertRolePermission(rolePermission);
        System.out.println(var1);

    }

    @Test
    public void findAllPermissionByRoleId() {
        List<Permission> permissions = rolePermissionDao.findAllPermissionByRoleId(1);
        System.out.println(permissions);

    }
}