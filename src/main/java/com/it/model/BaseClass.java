package com.it.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
