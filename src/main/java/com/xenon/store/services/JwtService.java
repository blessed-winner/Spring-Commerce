package com.xenon.store.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

public class JwtService {
    public String generateToken(String email){
        final long tokenExpiration = 86400;
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
                .signWith(Keys.hmacShaKeyFor("secret".getBytes()))
                .compact();

    }
}
