package com.guagua.dao.common;

import com.guagua.bean.entity.common.UserLoginLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-13 下午9:29
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class UserLoginLogDaoTest {

    @Autowired
    private UserLoginLogDao loginLogDao;

    @Test
    public void insertUserLoginLog() {
        UserLoginLog log = new UserLoginLog(1, "127.0.0.1");
        Integer var1 = loginLogDao.insertUserLoginLog(log);
        System.out.println(var1);
    }
}