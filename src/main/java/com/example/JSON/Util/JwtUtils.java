package com.example.JSON.Util;


import com.example.JSON.common.AccessDeniedException;
import com.example.JSON.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component

public class JwtUtils {

    private static String secret = "This_is_secret";
    private static long expiryDuration = 60 * 60;

    public String generateJwt(User user) {

        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration * 1000;


        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);

        //claims
        Claims claims = Jwts.claims()
                .setIssuer(user.getId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);

        //optional claims
        claims.put("type", user.getUsertype());
        claims.put("name", user.getName());
        claims.put("emailId", user.getEmailId());

        //generate jwt using claims
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

    public void verify(String authorization) throws Exception {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization);
        } catch (Exception e) {
            //throw new AccessDeniedException("Access Denied");
            throw new Exception();
        }
    }
}
