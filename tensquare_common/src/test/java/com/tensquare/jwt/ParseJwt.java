package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @author yzy
 * @classname ParseJwt
 * @description TODO
 * @create 2019-07-12 10:33
 */
public class ParseJwt {
    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("my salt here")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_pqawiLCJpYXQiOjE1NjI4OTk1MDEsImV4cCI6MTU2Mjg5OTU2MSwicm9sZSI6ImRhZCJ9.YI_hhM3TMdmXYaL7ZICrChnEEE59PQEJ11o1_X_-qf8")
                .getBody();

        System.out.println("用户Id：" + claims.getId());
        System.out.println("用户名：" + claims.getSubject());
        System.out.println("签名时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("过期时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
        System.out.println("用户角色：" + claims.get("role"));
    }
}
