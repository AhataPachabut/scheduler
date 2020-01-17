package com.it.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The type Service.
 */
@Entity
@Table(name = "SERVICES")
@NoArgsConstructor
@Data
public class Service extends BaseClass {

    @Column(nullable = false)
    @NotNull(message = "{service.name.notNull}")
    @NotEmpty(message = "{service.name.notEmpty}")
    private String name;
}
