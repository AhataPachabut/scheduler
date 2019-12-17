package com.it.controller;

import com.it.dto.EventRequestDto;
import com.it.dto.EventResponseDto;
import com.it.model.Event;
import com.it.repository.EventRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//crud
//dto
//db
//exceptions
//validate
//logger

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private Mapper mapper;

    @Autowired
    private EventRepository eventRepository;

    //fetch type problem
    //dto can helps with it
    @GetMapping
    public ResponseEntity<List<EventResponseDto>> readAll() {
        final List<Event> events = eventRepository.findAll();

        final List<EventResponseDto> responseDtoList = events.stream()
                .map((user) -> mapper.map(user, EventResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventResponseDto> create(@RequestBody EventRequestDto requestDto) {
        final Event event = mapper.map(requestDto, Event.class);
        eventRepository.save(event);

        final EventResponseDto responseDto = mapper.map(event, EventResponseDto.class);
        //EventResponseDto responseDto = new EventResponseDto();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventResponseDto> read(@PathVariable Long id) {
        final Event event = eventRepository.getOne(id);
        if (!id.equals(event.getId())) {
            throw new RuntimeException();
        }

        final EventResponseDto responseDto = mapper.map(event, EventResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventResponseDto> update(@PathVariable Long id, @RequestBody EventRequestDto requestDto) {
        if (!id.equals(eventRepository.getOne(id).getId())) {
            throw new RuntimeException();
        }

        final Event event = mapper.map(requestDto, Event.class);
        event.setId(id);
        eventRepository.save(event);

        final EventResponseDto responseDto = mapper.map(event, EventResponseDto.class);
        //EventResponseDto responseDto = new EventResponseDto();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        if (!id.equals(eventRepository.getOne(id).getId())) {
            throw new RuntimeException();
        }

        eventRepository.deleteById(id);
    }

}

