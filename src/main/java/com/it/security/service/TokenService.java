package com.it.security.service;

import com.it.model.Token;

public interface TokenService {

    Token generate(String username);

    Token refresh(String token);

    String extractUsername(String token);

    boolean validate(String token);
}
