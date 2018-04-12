package com.guagua.dao.common;

import com.guagua.bean.entity.common.CustomerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-4-11 下午1:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class CustomerInfoDaoTest {

    @Autowired
    private CustomerInfoDao infoDao;

    @Test
    public void insertOne() {

        CustomerInfo info = new CustomerInfo();
        info.setUuid("1111111");
        info.setName("ride");
        info.setProcessorId(15);
        info.setTaskId(12);

        System.out.println(infoDao.insertOne(info));
    }
}