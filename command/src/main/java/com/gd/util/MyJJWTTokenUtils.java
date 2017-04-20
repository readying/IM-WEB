package com.gd.util;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 使用jjwt来生成规范的token
 * jwt生成方法中设置token分三部分，header payload signature
 */
public class MyJJWTTokenUtils {
    public static String getMyJJWTToken(String id,String username,String password,String key){
        SecretKey myKey =  getKeyByString(key);
        String token =  createJWT(id,username,password,getKeyByString(key));
        return token;
    }
    //生成token
    public static String createJWT(String id,String username,String password,SecretKey myKey){
        //加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = myKey;
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(getSubject(username,password))
                .signWith(signatureAlgorithm,key);
        return builder.compact();
    }
    //解密token
    public static Claims parseJWT(SecretKey myKey,String jwt){
        Claims claims =  Jwts.parser().setSigningKey(myKey).parseClaimsJws(jwt).getBody();
        return claims;
    }
    //由string生成key
    public static SecretKey getKeyByString(String stringKey){
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    //生成subject,根据userdetail实体
    public static String getSubject(String username,String password){
        JSONObject jo = new JSONObject();
        jo.put("username",username);
        jo.put("password", password);
        return jo.toJSONString();
    }
}
