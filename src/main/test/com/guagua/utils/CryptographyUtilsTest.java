package com.guagua.utils;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CryptographyUtilsTest {

    @Test
    public void md5() {
        System.out.println(new Date().toString());
        System.out.println(CryptographyUtils.md5("admin","1234567890"));
    }
}