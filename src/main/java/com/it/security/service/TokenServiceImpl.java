package com.it.security.service;

import com.it.model.Token;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    private static final String INVALID_JWT_SIGNATURE_MESSAGE = "Invalid JWT signature";
    private static final String INVALID_JWT_TOKEN_MESSAGE = "Invalid JWT token";
    private static final String EXPIRED_JWT_TOKEN_MESSAGE = "Expired JWT token";
    private static final String UNSUPPORTED_JWT_TOKEN_MESSAGE = "Unsupported JWT token";
    private static final String JWT_CLAIMS_STRING_IS_EMPTY_MESSAGE = "JWT claims string is empty";

    private static final String JWT_SECRET = "secret";
    private static final String BEARER = "Bearer";

    private static final Integer JWT_ACCESS_EXPIRATION_MILLIS = 600000;
    private static final Integer JWT_RERFESH_EXPIRATION_MILLIS = 1200000;

    @Override
    public Token generate(String username) {
        return createToken(username);
    }

    @Override
    public Token refresh(String token) {
        final String username = extractUsername(token);
        return createToken(username);
    }

    @Override
    public String extractUsername(String token) {

        return
                Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public boolean validate(String token) {

        try {
            Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            LOGGER.error(INVALID_JWT_SIGNATURE_MESSAGE, e);
            throw e;
        } catch (MalformedJwtException e) {
            LOGGER.error(INVALID_JWT_TOKEN_MESSAGE, e);
            throw e;
        } catch (ExpiredJwtException e) {
            LOGGER.error(EXPIRED_JWT_TOKEN_MESSAGE, e);
            throw e;
        } catch (UnsupportedJwtException e) {
            LOGGER.error(UNSUPPORTED_JWT_TOKEN_MESSAGE, e);
            throw e;
        } catch (IllegalArgumentException e) {
            LOGGER.error(JWT_CLAIMS_STRING_IS_EMPTY_MESSAGE, e);
            throw e;
        }
    }

    private Token createToken(String username) {
        Token token = new Token();
        token.setAccessToken(
                Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_ACCESS_EXPIRATION_MILLIS))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact());
        token.setRefreshToken(
                Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_RERFESH_EXPIRATION_MILLIS))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact());
        token.setTokenType(BEARER);
        return token;
    }
}