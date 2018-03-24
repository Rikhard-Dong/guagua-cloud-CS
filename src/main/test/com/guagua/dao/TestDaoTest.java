package com.guagua.dao;

import com.guagua.bean.entity.TestEntiry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Time;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-14 下午2:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class TestDaoTest {

    @Autowired
    private TestDao testDao;

    @Test
    public void insertTest() {
        TestEntiry testEntiry = new TestEntiry("this is test",  Time.valueOf("101:29:54"));

        Integer var1 = testDao.insertTest(testEntiry);
        System.out.println("###### var ===> " + var1);
        System.out.println("#############" + testEntiry);
    }
}