package com.it.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Client extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "clients")
    private Set<Event> events;
}
