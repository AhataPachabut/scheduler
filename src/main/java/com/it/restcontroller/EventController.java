package com.it.restcontroller;

import com.it.model.Event;
import com.it.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//1. crud
//2. dto
//3. exceptions

@RestController
@RequestMapping("/events")
public class EventController {

//    @Autowired
//    private Mapper mapper;

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Event>> readAll() {
        final List<Event> events = eventRepository.findAll();
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
        event = eventRepository.getOne(id);
        eventRepository.setClient(id, event.getClient().getId());
        eventRepository.setService(id, event.getService().getId());
       // eventRepository.setResourse(id, event.getResources());
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
