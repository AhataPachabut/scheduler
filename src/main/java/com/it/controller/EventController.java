package com.it.controller;

import com.it.dto.request.EventRequestDto;
import com.it.dto.response.EventResponseDto;
import com.it.model.Event;
import com.it.repository.ClientRepository;
import com.it.repository.EventRepository;
import com.it.repository.ResourceRepository;
import com.it.repository.ServiceRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> readAll() {
        final List<Event> event = eventRepository.findAll();

        final List<EventResponseDto> responseDto = event.stream()
                .map((i) -> mapper.map(i, EventResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventResponseDto> create(@RequestBody EventRequestDto requestDto) {
        final Event event = mapper.map(requestDto, Event.class);
        event.setClient(clientRepository.findById(requestDto.getClient()).orElseThrow(() -> new RuntimeException()));
        event.setService(serviceRepository.findById(requestDto.getService()).orElseThrow(() -> new RuntimeException()));
        event.setResources(requestDto.getResources().stream()
                .map((i) -> resourceRepository.findById(i).orElseThrow(() -> new RuntimeException()))
                .collect(Collectors.toSet()));
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
        event.setClient(clientRepository.findById(requestDto.getClient()).orElseThrow(() -> new RuntimeException()));
        event.setService(serviceRepository.findById(requestDto.getService()).orElseThrow(() -> new RuntimeException()));
        event.setResources(requestDto.getResources().stream()
                .map((i) -> resourceRepository.findById(i).orElseThrow(() -> new RuntimeException()))
                .collect(Collectors.toSet()));
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

