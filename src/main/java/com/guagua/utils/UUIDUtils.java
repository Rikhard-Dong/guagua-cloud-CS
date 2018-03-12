package com.guagua.utils;

import java.util.UUID;

/**
 * @author ride
 * @date 18-3-12 下午12:15
 */
public class UUIDUtils {
    /**
     * 生成随机的uuid并去掉连接符号
     *
     * @return 生成结果
     */
    public static String getUUID() {
        String result = UUID.randomUUID().toString();
        // 去掉"-"
        return result.substring(0, 8) +
                result.substring(9, 13) +
                result.substring(14, 18) +
                result.substring(19, 23) +
                result.substring(24);
    }
}
