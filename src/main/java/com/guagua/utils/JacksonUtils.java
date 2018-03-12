package com.guagua.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ride
 * @date 18-3-12 下午1:07
 * <p>
 * json与java bean互相转换工具
 */
public class JacksonUtils {
    private static ObjectMapper objectMapper;

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class)
     * (2)转换为List,如List<Student>,将第二个参数传递为Student
     * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     *
     * @param jsonStr   json数据
     * @param valueType
     * @return
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * json数组转List
     *
     * @param jsonStr
     * @param valueTypeRef
     * @return
     */
    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 把JavaBean转换为json字符串
     *
     * @param object 转换为json
     * @return 转换后的json数据
     */
    public static String toJSon(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
