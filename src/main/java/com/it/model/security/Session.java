package com.it.model.security;

import org.springframework.security.core.token.Token;

/**
 * The type Session.
 */
public class Session {

    private Token accessToken;

    private Token refreshToken;

    /**
     * Gets access token.
     *
     * @return the access token
     */
    public Token getAccessToken() {
        return accessToken;
    }

    /**
     * Sets access token.
     *
     * @param accessToken the access token
     */
    public void setAccessToken(Token accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Gets refresh token.
     *
     * @return the refresh token
     */
    public Token getRefreshToken() {
        return refreshToken;
    }

    /**
     * Sets refresh token.
     *
     * @param refreshToken the refresh token
     */
    public void setRefreshToken(Token refreshToken) {
        this.refreshToken = refreshToken;
    }
}
