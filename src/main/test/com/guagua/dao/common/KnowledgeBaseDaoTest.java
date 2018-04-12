package com.guagua.dao.common;

import com.guagua.bean.entity.common.KnowledgeBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-4-10 下午6:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class KnowledgeBaseDaoTest {

    @Autowired
    private KnowledgeBaseDao baseDao;

    @Test
    public void findBaseIdByTaskId() {
        List<Integer> baseIds = baseDao.findBaseIdByTaskId(6);
        System.out.println(baseIds);
    }

}