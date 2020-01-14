package com.it.controller;

import com.it.dto.SessionResponseDTO;
import com.it.model.security.Session;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Auth controller.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Mapper mapper;

    /**
     * Authenticate user response entity.
     *
     * @param username the username
     * @param password the password
     * @return the response entity
     */
    @GetMapping("/signin")
    public ResponseEntity<SessionResponseDTO> authenticateUser(@RequestParam(required=false,name="username") String username, @RequestParam(required=false,name="password") String password) {

        //set an authenticated user
        //либо лог+парль либо userdet+request т.к. в пер
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //generate token
        final Session session = new Session();
        session.setAccessToken(tokenService.allocateToken(username));
        session.setRefreshToken(tokenService.allocateToken(username));

        final SessionResponseDTO responseDto = mapper.map(session, SessionResponseDTO.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Refresh token response entity.
     *
     * @param tokenKey the token key
     * @return the response entity
     */
    @GetMapping("/refreshtoken")
    public ResponseEntity<SessionResponseDTO> refreshToken(@RequestParam (required=false,name="token") String tokenKey) {

        Token token = tokenService.verifyToken(tokenKey);
        String username = token.getExtendedInformation();

        //generate token
        final Session session = new Session();
        session.setAccessToken(tokenService.allocateToken(username));
        session.setRefreshToken(tokenService.allocateToken(username));

        final SessionResponseDTO responseDto = mapper.map(session, SessionResponseDTO.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
