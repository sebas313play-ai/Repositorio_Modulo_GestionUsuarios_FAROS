package com.faros.gestionusuarios.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30";

    private Key getSignInKey() {

        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    }

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);

    }

    public <T> T extractClaim(
            String token,
            Function<Claims, T> resolver) {

        final Claims claims = extractAllClaims(token);

        return resolver.apply(claims);

    }

    public String generateToken(UserDetails userDetails) {

        return generateToken(new HashMap<>(), userDetails);

    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {

        return Jwts.builder()

                .setClaims(extraClaims)

                .setSubject(userDetails.getUsername())

                .setIssuedAt(new Date())

                .setExpiration(

                        new Date(

                                System.currentTimeMillis()

                                        + 1000 * 60 * 60 * 24

                        )

                )

                .signWith(

                        getSignInKey(),

                        SignatureAlgorithm.HS256

                )

                .compact();

    }

    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        final String username = extractUsername(token);

        return username.equals(userDetails.getUsername())

                && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {

        return extractExpiration(token)

                .before(new Date());

    }

    private Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);

    }

    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()

                .setSigningKey(getSignInKey())

                .build()

                .parseClaimsJws(token)

                .getBody();

    }

}