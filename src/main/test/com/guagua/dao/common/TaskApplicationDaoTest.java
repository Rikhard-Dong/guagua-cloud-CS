package com.guagua.dao.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-4-11 下午7:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class TaskApplicationDaoTest {

    @Autowired
    private TaskApplicationDao applicationDao;

    @Test
    public void countTaskByStatus() {

        System.out.println(applicationDao.countTaskByStatus(15, 2));

    }
}