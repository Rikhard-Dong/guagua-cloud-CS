package com.guagua.dao.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-4-2 下午1:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class TaskEmploymentDaoTest {

    @Autowired
    private TaskEmploymentDao taskEmploymentDao;

    @Test
    public void getTaskIdsByMemberId() {
    }

    @Test
    public void findAllCustomerServiceByEnterpriseId() {
        System.out.println(taskEmploymentDao.findAllCustomerServiceByEnterpriseId(12).size());
    }
}