package com.guagua.utils;

import com.auth0.jwt.JWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * @author ride
 * @date 18-3-13 下午4:13
 * <p>
 * 创建和解密JWT
 */
public class JWTUtils {

    // 加密秘钥
    private static final String SECRET_KEY = "secret";

    // 过期时间 3天
    private static final int TTL_TIME = 3 * 24 * 60 * 60 * 1000;


    /**
     * 生成秘钥实例
     *
     * @return key
     */
    private static Key getKeyInstance() {
        // return MacProvider.generateKey();
        // We will sign our JavaWebToken with our ApiKey secret
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    /**
     * 生成jwt
     *
     * @param claims 添加参数
     * @return token
     */
    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, getKeyInstance())
                .setExpiration(new Date(System.currentTimeMillis() + TTL_TIME))
                .compact();
    }

    public static Map<String, Object> verifyJWT(String jwt) {
        try {

            Map<String, Object> jwtClaims =
                    Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
            return jwtClaims;
        } catch (Exception e) {
            return null;
        }
    }

}
