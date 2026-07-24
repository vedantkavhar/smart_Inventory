package com.inventory.auth.security;
import java.security.Key;
import java.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Component public class JwtUtil { @Value("${jwt.secret}") private String secret; @Value("${jwt.expiration}") private long expiration; public String generateToken(String username){return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+expiration)).signWith(key(),SignatureAlgorithm.HS256).compact();} private Key key(){return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));} }
