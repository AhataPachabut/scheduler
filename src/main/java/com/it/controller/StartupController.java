package com.it.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping()
public class StartupController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /*
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String read(Model model) {
        model.addAttribute("users", eventRepository.findAll());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(Model model) {
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
    */
}

