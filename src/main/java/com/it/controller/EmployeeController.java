package com.it.controller;

import com.it.dto.request.EmployeeRequestDto;
import com.it.dto.response.EmployeeResponseDto;
import com.it.model.Employee;
import com.it.model.Resource;
import com.it.service.ResourceService;
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
 * The type Employee controller.
 */
@RestController
@RequestMapping("/employees")
@Transactional
public class EmployeeController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private Mapper mapper;

    /**
     * Read all response entity.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> readAll() {
        final List<Resource> entity = resourceService.findAllEmployee();

        final List<EmployeeResponseDto> responseDto = entity.stream()
                .map((i) -> mapper.map(i, EmployeeResponseDto.class))
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
    public ResponseEntity<EmployeeResponseDto> read(@PathVariable Long id) {
        Resource entity = resourceService.findById(id);

        final EmployeeResponseDto responseDto = mapper.map(entity, EmployeeResponseDto.class);
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
    public ResponseEntity<EmployeeResponseDto> create(@Valid @RequestBody EmployeeRequestDto requestDto) throws Exception {
        final Resource entity = mapper.map(requestDto, Employee.class);
        resourceService.save(entity);

        final EmployeeResponseDto responseDto = mapper.map(entity, EmployeeResponseDto.class);
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
    public ResponseEntity<EmployeeResponseDto> update(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDto requestDto) throws Exception {
        Resource entity = resourceService.findById(id);
        resourceService.update(entity);

        final EmployeeResponseDto responseDto = mapper.map(entity, EmployeeResponseDto.class);
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
        resourceService.deleteById(id);
    }
}

