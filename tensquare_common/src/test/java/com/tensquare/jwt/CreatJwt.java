package com.tensquare.jwt;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author yzy
 * @classname CreatJwt
 * @description TODO
 * @create 2019-07-12 10:15
 */
public class CreatJwt {
    public static void main(String[] args) {
        JwtBuilder builder = Jwts.builder()
                .setId("666")
                .setSubject("小马")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+60000))
                .claim("role", "dad")
                .signWith(SignatureAlgorithm.HS256, "my salt here");

        System.out.println(builder.compact());
    }
}
