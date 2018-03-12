package com.guagua.utils;

/**
 * @author ride
 * @date 18-3-12 下午5:40
 * <p>
 * 验证各种是否合法, 比如手机号邮箱之类的
 */
public class RegExpUtils {

    public static boolean isPhoneLegal(String phone) {
        String regExp = "^(13\\d|14[5|7]|15\\d|166|17[3|6|7]|18\\d)\\d{8}$";
        return phone.matches(regExp);
    }

}
