package com.it.mvncontroller;

import com.it.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String readAll(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(Model model) {
        model.addAttribute("users", eventRepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String read(Model model) {
        model.addAttribute("users", eventRepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("users", eventRepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        eventRepository.deleteById(id);
    }
}
