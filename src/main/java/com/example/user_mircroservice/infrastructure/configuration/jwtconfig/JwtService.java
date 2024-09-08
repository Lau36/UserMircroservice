package com.example.user_mircroservice.infrastructure.configuration.jwtconfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.micrometer.common.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String jwtSecretKey = System.getenv("JWT_SECRET");

    public String getToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    private String generateToken(Map<String,Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .compact();
    }

    public boolean isTokenValid(String token) {
        final String username = getUserNameFromToken(token);
        return (username.equals(getUserNameFromToken(token)) && !isTokenExpired(token));
    }

    public String getUserNameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    private Claims getAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(@NonNull String token, Function<Claims, T> claimsResolver) {
        final Claims claims= getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpiration (String token) {
        return getClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired (String token) {
        return getExpiration(token).before(new Date());
    }

}
