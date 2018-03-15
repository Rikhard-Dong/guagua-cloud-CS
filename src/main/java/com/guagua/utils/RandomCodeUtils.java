package com.guagua.utils;

import java.util.Random;

/**
 * @author ride
 * @date 18-3-15 下午2:36
 * <p>
 * 生成随机数, 用户手机验证码和邮箱验证码
 */
public class RandomCodeUtils {

    /**
     * 随机生成六位验证码
     *
     * @return 验证码
     */
    public static String randomCode() {
        StringBuilder number = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }
}
