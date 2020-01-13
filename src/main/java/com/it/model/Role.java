package com.it.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ROLES")
@NoArgsConstructor
@Data
public class Role extends BaseClass{

    @Column(unique = true, nullable = false)
    @NotNull(message = "{role.name.notNull}")
    @NotEmpty(message = "{role.name.notEmpty}")
    private String name;

//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;
}
