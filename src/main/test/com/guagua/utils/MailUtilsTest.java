package com.guagua.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-15 下午2:45
 */
public class MailUtilsTest {

    @Test
    public void run() {

        new Thread(new MailUtils("18888643093@163.com", "aaaa", "this is test"));
    }
}