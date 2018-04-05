package com.guagua.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ride
 * @date 18-3-12 下午5:40
 * <p>
 * 验证各种是否合法, 比如手机号邮箱之类的
 */
public class RegExpUtils {

    /**
     * 判断手机号是否合法
     *
     * @param phone 手机号
     * @return true false
     */
    public static boolean isPhoneLegal(String phone) {
        String regExp = "^(13\\d|14[5|7]|15\\d|166|17[3|6|7]|18\\d)\\d{8}$";
        return phone.matches(regExp);
    }

    /**
     * 判断邮箱是否合法
     *
     * @param email 邮箱
     * @return true false
     */
    public static boolean isEmailLegal(String email) {
        String regExp = "^[0-9a-zA-Z]+(?:[\\_\\.\\-][a-z0-9\\-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+$";
        return email.matches(regExp);
    }

    /**
     * 判断qq号是否合法
     *
     * @param qq qq
     * @return true false
     */
    public static boolean isQQLegal(String qq) {
        String regExp = "^[1-9][0-9]{4,}$";
        return qq.matches(regExp);
    }


    /**
     * 判断微信号是否合法
     *
     * @param wechat 微信号
     * @return true false
     */
    public static boolean isWecahtLegal(String wechat) {
        String regExp = "^[a-z_\\d]+$";
        return wechat.matches(regExp);
    }

    /**
     * 判断权限是否合法, 比如get:/user/*是权限, 可以匹配get:/user/1 之类
     *
     * @param var1 数据库的权限表示
     * @param var2 具体的权限显示
     * @return 结果
     */
    public static boolean isPermissionLegal(String var1, String var2) {
        var1 = StringUtils.removeAll(var1, "\\*");

        return StringUtils.indexOf(var2, var1) != -1;
    }

    /**
     * 匹配非负整数
     *
     * @param num
     * @return
     */
    public static boolean isInteger(String num) {
        String regExp = "^[1-9]d*|0$";
        return num.matches(regExp);
    }
}
