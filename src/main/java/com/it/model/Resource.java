package com.it.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The type Resource.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "RESOURCES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Data
public abstract class Resource extends BaseClass {

    @Column(nullable = false)
    @NotNull(message = "{resource.name.notNull}")
    @NotEmpty(message = "{resource.name.notEmpty}")
    private String name;

//    @ManyToMany(mappedBy = "resources")
//    private Set<Event> events;
}
