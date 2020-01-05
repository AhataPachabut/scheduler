package com.it.controller;

import com.it.dto.request.EventRequestDto;
import com.it.dto.response.EventResponseDto;
import com.it.model.Event;
import com.it.service.ClientService;
import com.it.service.EventService;
import com.it.service.ResourceService;
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
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventRepository;

    @Autowired
    private ClientService clientRepository;

    @Autowired
    private ServiceService serviceRepository;

    @Autowired
    private ResourceService resourceRepository;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> readAll() {
        final List<Event> entity = eventRepository.findAll();

        final List<EventResponseDto> responseDto = entity.stream()
                .map((i) -> mapper.map(i, EventResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventResponseDto> read(@PathVariable Long id) {
        Event entity = eventRepository.findById(id);

        final EventResponseDto responseDto = mapper.map(entity, EventResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventResponseDto> create(@Valid @RequestBody EventRequestDto requestDto) throws Exception {
        final Event entity = mapper.map(requestDto, Event.class);
//        entity.setClient(clientRepository.findById(requestDto.getClient()));
//        entity.setService(serviceRepository.findById(requestDto.getService()));
//        entity.setResources(requestDto.getResources().stream()
//                .map((i) -> resourceRepository.findById(i))
//                .collect(Collectors.toSet()));
        eventRepository.save(entity);

        final EventResponseDto responseDto = mapper.map(entity, EventResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventResponseDto> update(@PathVariable Long id, @Valid @RequestBody EventRequestDto requestDto) throws Exception {
        Event entity = eventRepository.findById(id);
//        entity.setClient(clientRepository.findById(requestDto.getClient()));
//        entity.setService(serviceRepository.findById(requestDto.getService()));
//        entity.setResources(requestDto.getResources().stream()
//                .map((i) -> resourceRepository.findById(i))
//                .collect(Collectors.toSet()));
        eventRepository.update(entity);

        final EventResponseDto responseDto = mapper.map(entity, EventResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        eventRepository.deleteById(id);
    }
}

