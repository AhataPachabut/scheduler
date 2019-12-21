package com.it.controller;

import com.it.dto.request.UserRequestDto;
import com.it.dto.response.TokenResponseDTO;
import com.it.dto.response.UserResponseDto;
import com.it.model.User;
import com.it.repository.RoleRepository;
import com.it.repository.UserRepository;
import com.it.security.service.TokenService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/refresh")
    public ResponseEntity<TokenResponseDTO> refreshToken(@RequestParam String token) {
        final TokenResponseDTO responseDto = new TokenResponseDTO();
        responseDto.setAccessToken(tokenService.refresh(token));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

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
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto requestDto) {
        final User user = new User();
        user.setName(requestDto.getName());
        user.setPassword(encoder.encode(requestDto.getPassword()));
        user.setRoles(requestDto.getRoles().stream()
                .map(roleRepository::findByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet()));

        final UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
