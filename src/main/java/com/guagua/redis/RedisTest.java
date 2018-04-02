package com.guagua.redis;

import com.guagua.redis.utils.SerializeUtils;
import com.guagua.websocket.entity.UserInfo;
import redis.clients.jedis.Jedis;

/**
 * @author ride
 * @date 18-3-30 下午7:25
 */
public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("47.100.113.55");
        jedis.auth("ABCabc123#");
        System.out.println("server is running: " + jedis.ping());

        // 测试
        String keys = "name";
        jedis.del(keys);

        String value = jedis.set(keys, "ride");
        System.out.println(value);

        System.out.println(jedis.get(keys));

//        UserInfo userInfo = new UserInfo(UserInfo.TYPE_MEMBER_USER);
//        userInfo.setUserId(1);
//        jedis.set(("user:" + userInfo.getUserId()).getBytes(), SerializeUtils.serialize(userInfo));
//
//
//        byte[] userInfoByte = jedis.get(("user:" + userInfo.getUserId()).getBytes());
//        UserInfo userInfoGet = (UserInfo) SerializeUtils.unserialize(userInfoByte);
//        System.out.println(userInfo);
    }

}
