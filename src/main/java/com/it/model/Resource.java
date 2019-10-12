package com.it.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "resources")
    private Set<Event> events;
}
