package com.guagua.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-18 下午9:40
 */
public class UrlUtilsTest {

    @Test
    public void match() {
        assertTrue(UrlUtils.match("get:/a/*/b", "get:/a/1/b", 0, 0));
//        assertFalse(UrlUtils.match("get:/a/*/b", "get:/a//b", 0, 0));
        assertTrue(UrlUtils.match("get:/a/*/b/*/c", "get:/a/1/b/2/c", 0, 0));
        assertFalse(UrlUtils.match("get:/a/*/b/*/c", "get:/a/1/b/2/c/123", 0, 0));

//        System.out.println(UrlUtils.match("get:/a/*/b", "get:/a//b", 0, 0));
    }
}