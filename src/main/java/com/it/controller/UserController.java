package com.it.controller;

import com.it.dto.request.UserRequestDto;
import com.it.dto.response.UserResponseDto;
import com.it.model.User;
import com.it.service.RoleService;
import com.it.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
//@Transactional
public class UserController {

    @Autowired
    private UserService userRepository;

    @Autowired
    private RoleService roleRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> readAll() {
        final List<User> entity = userRepository.findAll();

        final List<UserResponseDto> responseDto = entity.stream()
                .map((i) -> mapper.map(i, UserResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> read(@PathVariable Long id) {
        User user = userRepository.findById(id);

        final UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto requestDto) throws Exception {
        final User entity = mapper.map(requestDto, User.class);
        entity.setPassword(encoder.encode(requestDto.getPassword()));
        entity.setRoles(requestDto.getRoles().stream()
                .map((i) -> roleRepository.findById(i))
                .collect(Collectors.toSet()));
        userRepository.save(entity);

        final UserResponseDto responseDto = mapper.map(entity, UserResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @Valid @RequestBody UserRequestDto requestDto) throws Exception {
        User entity = userRepository.findById(id);
        entity.setRoles(requestDto.getRoles().stream()
                .map((i) -> roleRepository.findById(i))
                .collect(Collectors.toSet()));
        userRepository.update(entity);

        final UserResponseDto responseDto = mapper.map(entity, UserResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

}
