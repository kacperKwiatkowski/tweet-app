package com.kacperKwiatkowski.tweetApp.util;

import com.kacperKwiatkowski.tweetApp.security.jwt.JwtConfig;
import com.kacperKwiatkowski.tweetApp.security.role.RoleAuthorities;
import com.kacperKwiatkowski.tweetApp.security.role.RoleType;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Component
public class JwtTokenProvider {

    private static final String ROLE_PREFIX = "ROLE_";

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private SecretKey secretKey;

    public String successfulAuthentication() throws IOException, ServletException {
        return jwtConfig.getTokenPrefix() + Jwts.builder()
                .claim("authorities", Set.of(new SimpleGrantedAuthority(ROLE_PREFIX + RoleType.USER), new SimpleGrantedAuthority(RoleAuthorities.AUTHENTICATED.getPermission())))
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
    }
}
