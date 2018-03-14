package com.guagua.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author ride
 * @date 18-3-13 下午4:22
 */
public class JWTUtilsTest {

    @Test
    public void getKeyInstance() {


//        System.out.println(JWTUtils.getKeyInstance());
    }

    @Test
    public void createToken() {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("userId", 111);
        claims.put("username", "ride");

        System.out.println(JWTUtils.createToken(claims));
    }

    @Test
    public void verifyJWT() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MjA5MzA2MzIsInVzZXJJZCI6MTExLCJ1c2VybmFtZSI6InJpZGUifQ.e23bI04jt8eL9aCQp1Bfr5EuBQp5YGJClwZJag4OA4Q";

        Map<String, Object> claims = JWTUtils.verifyJWT(jwt);

        System.out.println(claims);

    }
}