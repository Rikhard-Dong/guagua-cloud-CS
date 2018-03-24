package com.guagua.dao.enterprise;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-24 下午12:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class EnterpriseAuthenticationDaoTest {
    @Autowired
    private EnterpriseAuthenticationDao authenticationDao;

    @Test
    public void findByEnterpriseIdAndPass() {

        System.out.println(authenticationDao.findByEnterpriseIdAndPass(12));
    }
}