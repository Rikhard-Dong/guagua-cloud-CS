package com.guagua.dao.common;

import com.guagua.bean.entity.common.KnowledgeBaseItem;
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
 * @date 18-4-10 下午7:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class KnowledgeBaseItemDaoTest {


    @Autowired
    private KnowledgeBaseItemDao itemDao;

    @Test
    public void searchKeyWord() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(12);
        list.add(13);

        List<KnowledgeBaseItem> knowledgeBaseItems = itemDao.searchKeyWord(list, "小爱同学");
        System.out.println("knowledge base items size ======> " + knowledgeBaseItems.size());
        System.out.println(knowledgeBaseItems);
    }
}