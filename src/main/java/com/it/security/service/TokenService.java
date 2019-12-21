package com.it.security.service;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String generate(Authentication authentication);

    String refresh(String token);

    String extractUsername(String token);

    boolean validate(String token);
}
