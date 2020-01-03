package com.it.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "RESOURCES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Resource extends BaseClass {

    @Column(nullable = false)
    @NotNull(message = "{resource.name.notNull}")
    @NotEmpty(message = "{resource.name.notEmpty}")
    private String name;

    @ManyToMany(mappedBy = "resources")
    private Set<Event> events;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
