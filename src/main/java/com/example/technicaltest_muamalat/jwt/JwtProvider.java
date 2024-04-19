package com.example.technicaltest_muamalat.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
@Service
public class JwtProvider {

//    private Key key;
//
//    @PostConstruct
//    public void init() {
//        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//    }

    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    String secretString = Encoders.BASE64.encode(key.getEncoded());


    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String jwt) {
        Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
        return true;
    }

    public String extractUsername(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();
        return claims.getSubject();
    }
}
