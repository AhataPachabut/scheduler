package com.it.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * The type User.
 */
@Entity
@Table(name = "USERS")
@NoArgsConstructor
@Data
public class User extends BaseClass{

    @Column(unique = true, nullable = false)
    @NotNull(message = "{user.name.notNull}")
    @NotEmpty(message = "{user.name.notEmpty}")
    private String name;

    @NotNull(message = "{user.password.notNull}")
    @NotEmpty(message = "{user.password.notEmpty}")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "User_Role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

//    @OneToOne(mappedBy = "user")
//    private Client client;
}
