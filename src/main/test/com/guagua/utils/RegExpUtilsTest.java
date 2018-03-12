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
}