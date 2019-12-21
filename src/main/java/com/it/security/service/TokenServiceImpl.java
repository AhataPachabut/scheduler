package com.it.security.service;

import com.it.security.model.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    private static final Integer JWT_EXPIRATION_MILLIS = 600000;

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    @Override
    public String generate(Authentication authentication) {
        final String username = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
        return generate(username);
    }

    @Override
    public String refresh(String token) {
        final String username = extractUsername(token);
        return generate(username);
    }

    @Override
    public String extractUsername(String token) {

        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();
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

    public String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION);
        return authHeader != null && authHeader.startsWith(BEARER)
                ? authHeader.replace(BEARER, "") : authHeader;
    }

    private String generate(String username) {
        return BEARER +
                Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MILLIS))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

}