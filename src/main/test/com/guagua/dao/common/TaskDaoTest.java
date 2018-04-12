package com.guagua.dao.common;

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
 * @date 18-4-11 下午1:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class TaskDaoTest {

    @Autowired
    private TaskDao taskDao;

    @Test
    public void countNumByType() throws ParseException {
        Date startTime = DateUtils.str2Date("2000-1-1 0:0:0");
        Date endTime = new Date();
        System.out.println(taskDao.countNumByStatus(2, startTime, endTime));
    }
}