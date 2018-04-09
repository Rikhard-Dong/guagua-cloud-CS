package com.guagua.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ride
 * @date 18-4-8 下午2:37
 */
public class PropertiesUtils {

    /**
     * 获取properties文件参数的值
     *
     * @param url
     * @param attr
     * @return
     * @throws IOException
     */
    public static String getProperties(String url, String attr) throws IOException {

//        InputStream in = new BufferedInputStream(new FileInputStream(url));
        InputStream in = PropertiesUtils.class.getResourceAsStream(url);
        Properties p = new Properties();
        p.load(in);

        return p.getProperty(attr);

    }

    public static void main(String[] args) throws IOException {
        System.out.println(getProperties("/properties/domain.properties", "guagua.domain"));
    }
}
