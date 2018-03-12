package com.guagua.utils;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-12 下午12:16
 */
public class UUIDUtilsTest {

    @Test
    public void getUUID() {
        String str = new Date().getTime() + UUIDUtils.getUUID();
        System.out.println("1234567890123456789012345678901234567890");
        str = str.substring(0, 32);
        System.out.println(str);
        System.out.println(UUIDUtils.getUUID());
    }
}