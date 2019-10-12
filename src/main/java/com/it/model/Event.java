package com.it.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToMany
    @JoinTable(name = "Event_Resource",
            joinColumns = {@JoinColumn(name = "resource_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private Set<Resource> resources;

}
