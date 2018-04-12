package com.guagua.dao.common;

import com.guagua.bean.entity.common.User;
import com.guagua.utils.CryptographyUtils;
import com.guagua.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-12 上午10:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void insertUser() {
        User user = new User("18888643093", "ride", "admin", 0);
        Integer flag = userDao.insertUser(user);
        assertTrue(flag != 0);
    }

    @Test
    public void validateAccount() {
        User user = userDao.findById(3);
        System.out.println("查询到的用户 ===> " + user);
        String password = CryptographyUtils.md5("admin", user.getSalt());
        System.out.println("md5加密密码为 ====> " + password);
        User validateUser = userDao.validateAccount(user.getPhone(), password);
        System.out.println(validateUser);
    }

    @Test
    public void findById() {
        User user = userDao.findById(15);
        System.out.println(user);
    }

    @Test
    public void findByPhone() {
        User user = userDao.findByPhone("18888643093");
        System.out.println(user);
    }

    @Test
    public void deleteById() {
        Integer flag = userDao.deleteById(1);
        assertTrue(flag != 0);
    }

    @Test
    public void countUserNum() throws ParseException {
        Date startTime = DateUtils.str2Date("2000-1-1 0:0:0");
        Date endTime = new Date();
        System.out.println(userDao.countUserNum(startTime, endTime));
    }

    @Test
    public void countUserNumByUserType() {
    }
}