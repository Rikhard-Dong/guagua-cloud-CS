package com.guagua.dao.enterprise;

import com.guagua.bean.entity.enterprise.BindTaskKnowledge;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-29 下午7:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class BindTaskKnowledgeDaoTest {

    @Autowired
    private BindTaskKnowledgeDao dao;

    @Test
    public void insertOne() {
        BindTaskKnowledge bindTaskKnowledge = new BindTaskKnowledge(2, 1);
        System.out.println(dao.insertOne(bindTaskKnowledge));
    }

    @Test
    public void insertBatch() {
        List<BindTaskKnowledge> bindTaskKnowledges = new ArrayList<BindTaskKnowledge>();
        bindTaskKnowledges.add(new BindTaskKnowledge(2, 4));
        bindTaskKnowledges.add(new BindTaskKnowledge(2, 8));
        dao.insertBatch(bindTaskKnowledges);
    }

    @Test
    public void findBaseByTaskId() {
        System.out.println(dao.findBaseByTaskId(2));
    }
}