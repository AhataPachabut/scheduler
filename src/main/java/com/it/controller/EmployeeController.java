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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private ResourceService resourceRepository;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> readAll() {
        final List<Resource> entity = resourceRepository.findAllEmployee();

        final List<EmployeeResponseDto> responseDto = entity.stream()
                .map((i) -> mapper.map(i, EmployeeResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeResponseDto> read(@PathVariable Long id) {
        Resource entity = resourceRepository.findById(id);

        final EmployeeResponseDto responseDto = mapper.map(entity, EmployeeResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> create(@Valid @RequestBody EmployeeRequestDto requestDto) throws Exception {
        final Resource entity = mapper.map(requestDto, Employee.class);
        resourceRepository.save(entity);

        final EmployeeResponseDto responseDto = mapper.map(entity, EmployeeResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeResponseDto> update(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDto requestDto) throws Exception {
        Resource entity = resourceRepository.findById(id);
        resourceRepository.update(entity);

        final EmployeeResponseDto responseDto = mapper.map(entity, EmployeeResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        resourceRepository.deleteById(id);
    }
}

