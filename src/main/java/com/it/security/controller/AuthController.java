package com.it.security.controller;

import com.it.security.dto.SessionResponseDTO;
import com.it.security.model.Session;
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

import javax.validation.Valid;

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

    @GetMapping("/signin")
    public ResponseEntity<SessionResponseDTO> authenticateUser(@Valid @RequestParam(required=false,name="username") String username, @Valid @RequestParam(required=false,name="password") String password) {

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

    @GetMapping("/refreshtoken")
    public ResponseEntity<SessionResponseDTO> refreshToken(@Valid @RequestParam String tokenKey) {

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
