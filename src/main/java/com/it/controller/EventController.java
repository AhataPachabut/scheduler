package com.it.controller;

import com.it.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/events")
    public String index(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "index";
    }

    @GetMapping("/events/{id}")
    public String edit(Model model) {
        model.addAttribute("users", eventRepository.findAll());
        return "index";
    }

    @PostMapping("/events/{id}")
    public String add(Model model) {
        model.addAttribute("users", eventRepository.findAll());
        return "index";
    }

    @PutMapping("/events/{id}")
    public String update(Model model) {
        model.addAttribute("users", eventRepository.findAll());
        return "index";
    }

    @DeleteMapping("/events/{id}")
    public String delete(Model model) {
        model.addAttribute("users", eventRepository.findAll());
        return "index";
    }
}
