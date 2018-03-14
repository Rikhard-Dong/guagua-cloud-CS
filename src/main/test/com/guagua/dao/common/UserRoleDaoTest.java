package com.guagua.dao.common;

import com.guagua.bean.entity.common.Role;
import com.guagua.bean.entity.common.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-14 下午12:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class UserRoleDaoTest {
    @Autowired
    private UserRoleDao userRoleDao;

    @Test
    public void insertUserRole() {
        UserRole userRole = new UserRole(12, 1);
        Integer var1 = userRoleDao.insertUserRole(userRole);
        System.out.println("####################" + var1);
    }

    @Test
    public void findRolesByUserId() {
        List<Role> roles = userRoleDao.findRolesByUserId(5);
        System.out.println(roles);
    }
}