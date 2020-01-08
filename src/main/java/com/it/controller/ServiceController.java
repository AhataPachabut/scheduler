package com.it.controller;

import com.it.dto.request.ServiceRequestDto;
import com.it.dto.response.ServiceResponseDto;
import com.it.model.Service;
import com.it.service.ServiceService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceRepository;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<ServiceResponseDto>> readAll() {
        final List<Service> entity = serviceRepository.findAll();

        final List<ServiceResponseDto> responseDto = entity.stream()
                .map((i) -> mapper.map(i, ServiceResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ServiceResponseDto> read(@PathVariable Long id) {
        Service entity = serviceRepository.findById(id);

        final ServiceResponseDto responseDto = mapper.map(entity, ServiceResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServiceResponseDto> create(@Valid @RequestBody ServiceRequestDto requestDto) throws Exception {
        final Service entity = mapper.map(requestDto, Service.class);
        serviceRepository.save(entity);

        final ServiceResponseDto responseDto = mapper.map(entity, ServiceResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ServiceResponseDto> update(@PathVariable Long id, @Valid @RequestBody ServiceRequestDto requestDto) throws Exception {
        Service entity = serviceRepository.findById(id);
        serviceRepository.update(entity);

        final ServiceResponseDto responseDto = mapper.map(entity, ServiceResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        serviceRepository.deleteById(id);
    }
}

