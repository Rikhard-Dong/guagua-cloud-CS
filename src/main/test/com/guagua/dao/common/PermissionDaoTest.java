package com.guagua.dao.common;

import com.guagua.bean.entity.common.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-14 下午5:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class PermissionDaoTest {
    @Autowired
    private PermissionDao permissionDao;

    @Test
    public void insertPermission() {
        Permission permission = new Permission(1, "获取用户基本信息", "get:/user/profile/info/simple",
                "获取最基本的信息, 包括头像和用户名", 1);

        Integer var1 = permissionDao.insertPermission(permission);
        System.out.println(var1);
    }
}