package com.guagua.utils;

import com.guagua.bean.entity.common.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-12 下午1:10
 */
public class JacksonUtilsTest {

    @Test
    public void toJSon() {
        User user = new User("18888643093", "admin", "admin", 1);
        System.out.println(JacksonUtils.toJSon(user));

    }
}