package com.guagua.dao.common;

import com.guagua.bean.entity.common.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-14 下午3:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class MenuDaoTest {

    @Autowired
    private MenuDao menuDao;

    @Test
    public void insertMenu() {
        Menu menu = new Menu(1, "用户信息",
                "维护用户信息, 更新密码, 绑定/更换邮箱, 维护个人基础信息, 更换头像之类, 一级菜单", null);

        Integer var = menuDao.insertMenu(menu);
        System.out.println(var);
    }
}