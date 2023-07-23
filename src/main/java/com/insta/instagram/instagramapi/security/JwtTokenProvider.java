package com.insta.instagram.instagramapi.security;

import com.insta.instagram.instagramapi.utils.RSAKeyProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private RSAKeyProperties keys;

    @Autowired
    private JwtDecoder jwtDecoder;
    public JwtTokenProvider(RSAKeyProperties keys) {
        this.keys = keys;
    }



    public JwtTokenClaims getClaimsFromToken(String token){
        Map<String, Object> claims =  jwtDecoder.decode(token).getClaims();

        String username = String.valueOf(claims.get("sub"));

        JwtTokenClaims jwtTokenClaims = new JwtTokenClaims();
        jwtTokenClaims.setUsername(username);

        return jwtTokenClaims;
    }
}
