package com.it.controller;

import com.it.model.Event;
import com.it.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//1. crud
//2. dto
//3. exceptions
//4. validate
//5. logger

@RestController
@RequestMapping("/events")
public class EventController {

//    @Autowired
//    private Mapper mapper;

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Event>> readAll() {
        //final List<Event> events = eventRepository.findAll();
        //fetch type problem
        //dto can helps with it
        Event event = new Event();
        List<Event> events = new ArrayList<>();
        events.add(event);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Event> read(@PathVariable Long id) {
        final Event event = eventRepository.getOne(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Event> create(@RequestBody Event event) {
        eventRepository.save(event);
        event = eventRepository.getOne(event.getId());
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody Event event) {
        if (eventRepository.getOne(id) == null) {
            throw new RuntimeException();
        }
        eventRepository.save(event);
        event = eventRepository.getOne(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        eventRepository.deleteById(id);
    }

//    private Event getUser(EventRequestDto eventDto) {
//        final User user = mapper.map(userRequestDto, User.class);
//        final Role role = new Role();
//        role.setId(userRequestDto.getRoleId());
//        user.setRole(role);
//        return user;
//    }
}

