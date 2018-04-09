package com.guagua.dao.question;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-4-8 下午3:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class RuleOfGenerationExaminationDaoTest {

    @Autowired
    private RuleOfGenerationExaminationDao ruleDao;


    @Test
    public void findByBankId() {
        System.out.println(ruleDao.findByBankId(1));
    }
}