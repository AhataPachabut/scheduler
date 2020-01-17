package com.it.model.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.token.Token;

/**
 * The type Session.
 */
@NoArgsConstructor
@Data
public class Session {

    private Token accessToken;

    private Token refreshToken;
}
