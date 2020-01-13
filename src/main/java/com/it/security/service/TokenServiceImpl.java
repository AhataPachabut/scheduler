package com.it.security.service;

import com.it.security.model.TokenImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String JWT_SECRET = "secret";
    private static final String BEARER = "Bearer ";

    private static final Integer JWT_ACCESS_EXPIRATION_MILLIS = 600000;
    private static final Integer JWT_RERFESH_EXPIRATION_MILLIS = 1200000;

    @Override
    public Token allocateToken(String username) {

        String key =
                Jwts.builder()
                        .setSubject(username)
                        .setIssuedAt(new Date())
                        //.setExpiration(new Date(System.currentTimeMillis() + JWT_ACCESS_EXPIRATION_MILLIS))
                        .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                        .compact();

        key = BEARER + key;

        Token token = new TokenImpl();
        ((TokenImpl) token).setKey(key);
        ((TokenImpl) token).setExtendedInformation(username);

        return token;
    }

    @Override
    public Token verifyToken(String key) {

        key = key.replace(BEARER, "");

        String username =
                Jwts.parser()
                        .setSigningKey(JWT_SECRET)
                        .parseClaimsJws(key)
                        .getBody()
                        .getSubject();

        return allocateToken(username);
    }
}