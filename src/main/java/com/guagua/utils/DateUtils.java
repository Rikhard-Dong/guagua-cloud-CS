package com.guagua.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ride
 * @date 18-3-14 下午9:19
 * <p>
 * 时间日期工具类
 */
public class DateUtils {

    // 时间格式化
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 时间格式化中文版
    private static SimpleDateFormat formatCN = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");

    /**
     * 将日期时间类型转换为字符串
     *
     * @param date date
     * @return 转换后的字符串
     */
    public static String date2Str(Date date) {
        return format.format(date);
    }

    /**
     * 将日期时间类型转换为字符串, 转换为中文
     *
     * @param date date
     * @return 转换后的字符串
     */
    public static String date2StrCN(Date date) {
        return formatCN.format(date);
    }

    /**
     * 将制定的格式的字符串转换为日期时间类型
     *
     * @param date date
     * @return 转换后的date
     * @throws ParseException 解析失败异常
     */
    public static Date str2Date(String date) throws ParseException {
        return format.parse(date);
    }

}
