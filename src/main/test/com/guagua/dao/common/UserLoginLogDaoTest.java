package com.guagua.dao.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guagua.bean.entity.common.UserLoginLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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

    @Test
    public void findAll() {
        PageHelper.startPage(2, 10);
        List<UserLoginLog> logs = loginLogDao.findAll();
        System.out.println("##########################################################");
        System.out.println(logs);
        System.out.println("##########################################################");
        PageInfo<UserLoginLog> info = new PageInfo<UserLoginLog>(logs);
        System.out.println(info);
    }
}