package com.it.controller;

import com.it.dto.request.UserRequestDto;
import com.it.dto.response.RoleResponseDto;
import com.it.model.Role;
import com.it.service.RoleService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Role controller.
 */
@RestController
@RequestMapping("/roles")
@Transactional
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private Mapper mapper;

    /**
     * Read all response entity.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> readAll() {
        final List<Role> entity = roleService.findAll();

        final List<RoleResponseDto> responseDto = entity.stream()
                .map((i) -> mapper.map(i, RoleResponseDto.class))
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
    public ResponseEntity<RoleResponseDto> read(@PathVariable Long id) {
        Role entity = roleService.findById(id);

        final RoleResponseDto responseDto = mapper.map(entity, RoleResponseDto.class);
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
    public ResponseEntity<RoleResponseDto> create(@Valid @RequestBody UserRequestDto requestDto) throws Exception {
        final Role entity = mapper.map(requestDto, Role.class);
        roleService.save(entity);

        final RoleResponseDto responseDto = mapper.map(entity, RoleResponseDto.class);
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
    public ResponseEntity<RoleResponseDto> update(@PathVariable Long id, @Valid @RequestBody UserRequestDto requestDto) throws Exception {
        Role entity = roleService.findById(id);
        roleService.update(entity);

        final RoleResponseDto responseDto = mapper.map(entity, RoleResponseDto.class);
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
        roleService.deleteById(id);
    }

}
