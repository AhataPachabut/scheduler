package com.it.controller;

import com.it.dto.request.UserRequestDto;
import com.it.dto.response.UserResponseDto;
import com.it.model.User;
import com.it.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/users")
@Transactional
//transactional+get or eager
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    /**
     * Read all response entity.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> readAll() {
        final List<User> entity = userService.findAll();

        final List<UserResponseDto> responseDto = entity.stream()
                .map((i) -> mapper.map(i, UserResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Read response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> read(@PathVariable Long id) {
        User user = userService.findById(id);

        final UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Create response entity.
     *
     * @param requestDto the request dto
     * @return the response entity
     * @throws Exception the exception
     */
    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto requestDto) throws Exception {
        final User entity = mapper.map(requestDto, User.class);
        entity.setPassword(encoder.encode(requestDto.getPassword()));
        userService.save(entity);

        final UserResponseDto responseDto = mapper.map(entity, UserResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Update response entity.
     *
     * @param id         the id
     * @param requestDto the request dto
     * @return the response entity
     * @throws Exception the exception
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @Valid @RequestBody UserRequestDto requestDto) throws Exception {
        User entity = userService.findById(id);
        entity.setPassword(encoder.encode(requestDto.getPassword()));
        userService.update(entity);

        final UserResponseDto responseDto = mapper.map(entity, UserResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
