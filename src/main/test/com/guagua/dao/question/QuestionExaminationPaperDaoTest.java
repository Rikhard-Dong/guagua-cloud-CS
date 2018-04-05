package com.guagua.dao.question;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-4-5 下午1:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class QuestionExaminationPaperDaoTest {


    @Autowired
    private QuestionExaminationPaperDao questionExaminationPaperDao;

    @Test
    public void countByQuestionTypeAndExamination() {

        System.out.println(questionExaminationPaperDao.countByQuestionTypeAndExamination(1, 0));
    }
}