package com.guagua.redis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author ride
 * @date 18-3-30 下午8:02
 * <p>
 * 序列化和反序列化
 */
public class SerializeUtils {

    private final static Logger logger = LoggerFactory.getLogger(SerializeUtils.class);

    /**
     * 序列化对象
     *
     * @param object 待序列化对象
     * @return 序列化结果
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (IOException e) {
            logger.error("序列化失败");

            e.printStackTrace();
        }

        return null;
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;

        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException e) {
            logger.error("反序列化失败: IO异常");

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            logger.error("反序列化失败: ClassNotFound异常");
            e.printStackTrace();
        }

        return null;
    }
}
