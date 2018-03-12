package com.guagua.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 密码加密类
 */
public class CryptographyUtils {
    // 散列次数
    private static final Integer HASH_ITERATIONS = 4;

    /**
     * 盐值加密密码
     *
     * @param str  原始密码
     * @param slat 盐
     * @return 盐值加密后的密码
     */
    public static String md5(String str, String slat) {
        return new Md5Hash(str, slat, HASH_ITERATIONS).toString();
    }
}
