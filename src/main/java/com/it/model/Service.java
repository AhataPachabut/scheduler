package com.it.model;

import javax.persistence.*;
import java.util.Set;

public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "services")
    private Set<Event> events;
}
