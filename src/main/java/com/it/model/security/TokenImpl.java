package com.it.model.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.token.Token;

/**
 * The type Token.
 */
@NoArgsConstructor
@Data
public class TokenImpl implements Token {

    private String key;

    private String extendedInformation;

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
