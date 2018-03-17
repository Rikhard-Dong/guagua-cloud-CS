package com.guagua.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-12 下午5:57
 */
public class RegExpUtilsTest {

    @Test
    public void isPhoneLegal() {
        assertTrue(RegExpUtils.isPhoneLegal("18888643093"));
        assertFalse(RegExpUtils.isPhoneLegal("11111111111"));
    }

    @Test
    public void isEmailLegal() {
        assertTrue(RegExpUtils.isEmailLegal("1270458214@qq.com"));
        assertFalse(RegExpUtils.isEmailLegal("1270458214qq.com"));
        assertFalse(RegExpUtils.isEmailLegal("12704582#@14qq.com"));
        assertFalse(RegExpUtils.isEmailLegal("_70458214@qq.com"));
        assertTrue(RegExpUtils.isEmailLegal("127045_8214@qq.com"));
    }

    @Test
    public void isPermissionLegal() {

        System.out.println(RegExpUtils.isPermissionLegal("get:/user/*", "get:/use1r/123"));
    }
}