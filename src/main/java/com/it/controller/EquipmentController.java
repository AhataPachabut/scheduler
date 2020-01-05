package com.it.controller;

import com.it.dto.request.EquipmentRequestDto;
import com.it.dto.response.EquipmentResponseDto;
import com.it.model.Equipment;
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

//crud+
//dto+
//mapper+-
//db+
//exceptions+-
//validate+-
//logger
//spring security+-

@RestController
@RequestMapping("/equipments")
public class EquipmentController {

    @Autowired
    private ResourceService resourceRepository;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<EquipmentResponseDto>> readAll() {
        final List<Resource> entity = resourceRepository.findAllEquipment();

        final List<EquipmentResponseDto> responseDto = entity.stream()
                .map((i) -> mapper.map(i, EquipmentResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EquipmentResponseDto> read(@PathVariable Long id) {
        Resource entity = resourceRepository.findById(id);

        final EquipmentResponseDto responseDto = mapper.map(entity, EquipmentResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EquipmentResponseDto> create(@Valid @RequestBody EquipmentRequestDto requestDto) throws Exception {
        final Resource entity = mapper.map(requestDto, Equipment.class);
        resourceRepository.save(entity);

        final EquipmentResponseDto responseDto = mapper.map(entity, EquipmentResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EquipmentResponseDto> update(@PathVariable Long id, @Valid @RequestBody EquipmentRequestDto requestDto) throws Exception {
        Resource entity = resourceRepository.findById(id);
        resourceRepository.update(entity);

        final EquipmentResponseDto responseDto = mapper.map(entity, EquipmentResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        resourceRepository.deleteById(id);
    }
}

