package com.it.controller;

import com.it.dto.request.UserRequestDto;
import com.it.dto.response.UserResponseDto;
import com.it.model.User;
import com.it.repository.RoleRepository;
import com.it.repository.UserRepository;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private static final String SEMICOLON = ";";
    private static final String EMPTY = "";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping
    @Transactional
    public ResponseEntity<List<UserResponseDto>> readAll() {
        final List<User> user = userRepository.findAll();

        final List<UserResponseDto> responseDto = user.stream()
                .map((i) -> mapper.map(i, UserResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto requestDto){
        final User user = mapper.map(requestDto, User.class);
        user.setPassword(encoder.encode(requestDto.getPassword()));
        user.setRoles(requestDto.getRoles().stream()
                .map((i) -> roleRepository.findById(i).orElseThrow(() -> new RuntimeException()))
                .collect(Collectors.toSet()));
        userRepository.save(user);

        final UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<UserResponseDto> read(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());

        final UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
        user.setRoles(requestDto.getRoles().stream()
                .map((i) -> roleRepository.findById(i).orElseThrow(() -> new RuntimeException()))
                .collect(Collectors.toSet()));
        userRepository.save(user);

        final UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Transactional
    public void delete(@PathVariable Long id) {
        userRepository.findById(id).orElseThrow(() -> new RuntimeException());
        userRepository.deleteById(id);
    }

}
