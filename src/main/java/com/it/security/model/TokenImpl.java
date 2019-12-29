package com.it.security.model;

import org.springframework.security.core.token.Token;

public class TokenImpl implements Token {

    private String key;

    private String extendedInformation;

    public void setKey(String key) {
        this.key = key;
    }

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
