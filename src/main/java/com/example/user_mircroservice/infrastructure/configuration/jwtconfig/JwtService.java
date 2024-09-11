package com.example.user_mircroservice.infrastructure.configuration.jwtconfig;

import com.example.user_mircroservice.infrastructure.configuration.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.micrometer.common.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String jwtSecretKey = System.getenv(Constants.TOKEN_KEY);

    public String getToken(UserDetails userDetails, Long userId) {
        return generateToken(new HashMap<>(), userDetails, userId);
    }

    private String generateToken(Map<String,Object> extraClaims, UserDetails userDetails, Long userId) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .claim("role", getAuthority(userDetails.getAuthorities()))
                .claim( "User_id", userId)
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

    private String getAuthority(Collection<? extends GrantedAuthority> authorities) {
        return authorities.isEmpty() ? "" : authorities.iterator().next().getAuthority();
    }

}
