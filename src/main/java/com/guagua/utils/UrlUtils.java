package com.guagua.utils;

public class UrlUtils {

    /**
     * 匹配url
     *
     * @param pattern 匹配模式
     * @param content 匹配内容
     * @param p 0
     * @param c 0
     * @return true false
     */
    public static boolean match(String pattern, String content, int p, int c) {
        if (pattern.length() == p && content.length() == c)
            return true;
        if (pattern.length() > p && '*' == pattern.charAt(p) && pattern.length() > p + 1 && content.length() == c)
            return false;
        if (pattern.length() > p && content.length() > c && ('?' == pattern.charAt(p) || pattern.charAt(p) == content.charAt(c)))
            return match(pattern, content, p + 1, c + 1);
        if (pattern.length() > p && '*' == pattern.charAt(p))
            return match(pattern, content, p + 1, c) || match(pattern, content, p, c + 1);
        return false;
    }
}