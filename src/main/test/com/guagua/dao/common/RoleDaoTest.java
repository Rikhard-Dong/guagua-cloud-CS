package com.guagua.dao.common;

import com.guagua.bean.entity.common.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-14 下午12:44
 * <p>
 * role dao test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class RoleDaoTest {

    @Autowired
    private RoleDao roleDao;

    @Test
    public void insertRole() {
        Role role = new Role("base", "基础用户角色, 拥有通用的权限, 比如维护个人信息, 更新头像密码等等");
        Integer var1 = roleDao.insertRole(role);
        System.out.println("##################" + var1);
    }
}