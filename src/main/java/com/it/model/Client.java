package com.it.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The type Client.
 */
@Entity
@Table(name = "CLIENTS")
@NoArgsConstructor
@Data
public class Client extends BaseClass {

    @Column(nullable = false)
    @NotNull(message = "{client.name.notNull}")
    @NotEmpty(message = "{client.name.notEmpty}")
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
