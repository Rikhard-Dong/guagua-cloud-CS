package com.guagua;

import com.alibaba.druid.support.json.JSONUtils;
import com.guagua.enums.DataDictionary;
import com.guagua.utils.JacksonUtils;
import com.guagua.websocket.entity.Message;
import org.junit.Test;

/**
 * @author ride
 * @date 18-3-12 下午1:21
 */

public class MyTest {

    @Test
    public void Test() {
        String json = "{msg:\"nihao\",\n" +
                "receiver:\"11\",\n" +
                "receiverId:\"18\",\n" +
                "sendTime:1522668538803,\n" +
                "sendTimeStr:\"2018-04-02 19:29:06\",\n" +
                "sender:\"22\",\n" +
                "senderId:\"12\",\n" +
                "type:0}";

        json = "{\"senderId\":12,\"sender\":\"发送者:12\",\"receiverId\":15,\"receiver\":\"接收者:15\",\"msg\":\"这是一个测试\",\"sendTime\":1522480650815,\"type\":0}";

        Message var = JacksonUtils.readValue(json, Message.class);
        System.out.println(var);
    }
}
