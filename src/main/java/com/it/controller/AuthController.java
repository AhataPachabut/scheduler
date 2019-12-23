package com.it.controller;

import com.it.dto.response.TokenResponseDTO;
import com.it.model.Session;
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
    private Session userRepository;

    @Autowired
    private Mapper mapper;

    @GetMapping("/signin")
    public ResponseEntity<TokenResponseDTO> authenticateUser(@RequestParam(required=false,name="username") String username, @RequestParam(required=false,name="password") String password) {
        //set an authenticated user
        //либо лог+парль либо userdet+request т.к. в пер
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //return token
        final TokenResponseDTO responseDto = new TokenResponseDTO();
        responseDto.setAccessToken(tokenService.generate(authentication));
        responseDto.setRefreshToken(tokenService.generate(authentication));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/refreshtoken")
    public ResponseEntity<TokenResponseDTO> refreshToken(@RequestParam String token) {
        final TokenResponseDTO responseDto = new TokenResponseDTO();
        responseDto.setAccessToken(tokenService.refresh(token));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
