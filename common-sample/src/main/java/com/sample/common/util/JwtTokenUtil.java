package com.sample.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.common.model.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {
    private static Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    public static final String AUTH_HEADER_KEY = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static long expiresSecond=720000000;
    /**
     * 解析jwt
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJWT(String jsonWebToken,String base64Secret) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                .parseClaimsJws(jsonWebToken).getBody();
        return claims;
    }

    /**
     * 构建jwt
     * @return
     */
    public static String createJWT(Map<String,Object> uesrInfo, String base64Secret) {
        // 使用HS256加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT");
                // 可以将基本不重要的对象信息放到claims
                //.claim("role", roleList)
                for(Map.Entry<String,Object> entry:uesrInfo.entrySet()) {
                    builder.claim(entry.getKey(),entry.getValue());
                }
                //.setSubject(username)           // 代表这个JWT的主体，即它的所有人
               // .setIssuer(jwtConfig.getIssuer())              // 代表这个JWT的签发主体；
                //.setIssuedAt(new Date())        // 是一个时间戳，代表这个JWT的签发时间；
                //.setAudience(audience.getName())          // 代表这个JWT的接收对象；
                builder.signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        long TTLMillis = expiresSecond;
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                    .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
        }
        //生成JWT
        return builder.compact();
    }

    /**
     * 从token中获取用户ID
     * @param token
     * @param base64Security
     * @return
     */
    public static <T> T getJWTInfo(String token, String base64Security,Class<T> clazz) {
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(parseJWT(token,base64Security)),clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        // return parseJWT(token,base64Security).get("userId", String.class);
    }


    public static void main(String[] args) {
        String base64Secret="MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";
        Map<String,Object> param = new HashMap<>();
        param.put("userId", 10298);
        param.put("userName", "三丰");
        param.put("userAccount","ahn");
        String jwtToken = JwtTokenUtil.createJWT(param,base64Secret);
       // jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbImFkbWluIiwicm9sZSJdLCJ1c2VyTmFtZSI6InpoYW5nc2FuZiIsImV4cCI6MTU5MTg3NjQxMCwibmJmIjoxNTkxODY5MjEwfQ.0p1kDAQ7mmCc7uiaYru0rgk10s4rboIBF5TJzZMTM7S";
        System.out.println("token:"+jwtToken);
        Claims parseJWT = JwtTokenUtil.parseJWT(jwtToken,base64Secret);
        System.out.println("Claims:"+parseJWT);
        System.out.println("JWTInfo:"+JwtTokenUtil.getJWTInfo(jwtToken,base64Secret, UserInfo.class));
    }
}
