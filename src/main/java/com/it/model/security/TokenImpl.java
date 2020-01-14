package com.it.model.security;

import org.springframework.security.core.token.Token;

/**
 * The type Token.
 */
public class TokenImpl implements Token {

    private String key;

    private String extendedInformation;

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Sets extended information.
     *
     * @param extendedInformation the extended information
     */
    public void setExtendedInformation(String extendedInformation) {
        this.extendedInformation = extendedInformation;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public long getKeyCreationTime() {
        return 0;
    }

    @Override
    public String getExtendedInformation() {
        return extendedInformation;
    }
}
