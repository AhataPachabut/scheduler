package com.it.controller;

import com.it.dto.response.TokenResponseDTO;
import com.it.security.service.TokenService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private Mapper mapper;

    @GetMapping("/signin")
    public ResponseEntity<TokenResponseDTO> authenticateUser(@RequestParam(required=false,name="username") String username, @RequestParam(required=false,name="password") String password) {
        //set an authenticated user
        //либо лог+парль либо userdet+request т.к. в пер
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //generate token
        final TokenResponseDTO responseDto = mapper.map(tokenService.generate(username), TokenResponseDTO.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/refreshtoken")
    public ResponseEntity<TokenResponseDTO> refreshToken(@RequestParam String token) {
        //refresh token
        final TokenResponseDTO responseDto = mapper.map(tokenService.refresh(token), TokenResponseDTO.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
