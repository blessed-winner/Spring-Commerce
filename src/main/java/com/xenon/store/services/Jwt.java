package com.xenon.store.services;

import com.xenon.store.entities.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

public class Jwt {
    private final Claims claims;
    private final SecretKey secretKey;

    public Jwt(Claims claims, SecretKey secretKey){
        this.claims = claims;
        this.secretKey = secretKey;
    }

    public boolean isExpired(){
        return claims.getExpiration().before(new Date());
    }

    public Long getUserId(String token){
        return Long.valueOf(claims.getSubject());
    }

    public Role getRole(String token){
        return Role.valueOf((claims.get("role",String.class)));
    }

    @Override
    public String toString() {
       return Jwts.builder().claims(claims).signWith(secretKey).compact();
    }
}
