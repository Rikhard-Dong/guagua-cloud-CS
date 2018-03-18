package com.guagua.dao.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-18 下午3:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class MessageDaoTest {

    @Autowired
    private MessageDao messageDao;

    @Test
    public void findAllByUserId() {
        System.out.println(messageDao.findAllByUserId(15));

    }
}