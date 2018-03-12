package com.guagua.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-12 下午4:38
 */
public class SMSCodeUtilsTest {

    @Test
    public void sendAlicomMsgDemo() {
        String code = SMSCodeUtils.SendAlicomMsgDemo("18888643093");
        System.out.println(code);
    }
}