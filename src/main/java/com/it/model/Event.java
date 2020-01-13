package com.it.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "EVENTS")
@NoArgsConstructor
@Data
public class Event extends BaseClass {

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Event_Resource",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id")})
    private Set<Resource> resources;

}
