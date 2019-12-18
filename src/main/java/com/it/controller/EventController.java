package com.it.controller;

import com.it.dto.request.EventRequestDto;
import com.it.dto.responce.EventResponseDto;
import com.it.model.Event;
import com.it.repository.ClientRepository;
import com.it.repository.EventRepository;
import com.it.repository.ServiceRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//crud
//dto
//mapper
//db
//exceptions
//validate
//logger
//spring security

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private Mapper mapper;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> readAll() {
        final List<Event> events = eventRepository.findAll();

        final List<EventResponseDto> responseDto = events.stream()
                .map((user) -> mapper.map(user, EventResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventResponseDto> create(@RequestBody EventRequestDto requestDto) {
        Event event = new Event();
        event.setClient(clientRepository.findById(requestDto.getClientId()).orElseThrow(() -> new RuntimeException()));
        event.setService(serviceRepository.findById(requestDto.getServiceId()).orElseThrow(() -> new RuntimeException()));
        eventRepository.save(event);

        final EventResponseDto responseDto = mapper.map(event, EventResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventResponseDto> read(@PathVariable Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException());

        final EventResponseDto responseDto = mapper.map(event, EventResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventResponseDto> update(@PathVariable Long id, @RequestBody EventRequestDto requestDto) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException());
        event.setClient(clientRepository.findById(requestDto.getClientId()).orElseThrow(() -> new RuntimeException()));
        event.setService(serviceRepository.findById(requestDto.getServiceId()).orElseThrow(() -> new RuntimeException()));
        eventRepository.save(event);

        final EventResponseDto responseDto = mapper.map(event, EventResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        eventRepository.findById(id).orElseThrow(() -> new RuntimeException());
        eventRepository.deleteById(id);
    }
}

