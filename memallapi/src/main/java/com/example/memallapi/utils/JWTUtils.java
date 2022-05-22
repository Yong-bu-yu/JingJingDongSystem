package com.example.memallapi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.memallapi.entity.User;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    public static String createToken(User user){
        Date date = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR,3);
        Date exp = nowTime.getTime();

        Map<String,Object> map = new HashMap<>();
        map.put("alg","HMAC256");
        map.put("type","JWT");

        return JWT.create().withHeader(map)
                .withExpiresAt(exp)
                .withIssuedAt(date)
                .withClaim("userName",user.getName())
                .sign(Algorithm.HMAC256("Yong-bu-yu"));
    }

    public static String verifyToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("Yong-bu-yu")).build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> result = jwt.getClaims();
        return result.get("userName").asString();
    }
}
