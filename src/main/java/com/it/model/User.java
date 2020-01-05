package com.it.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User extends BaseClass{

    @Column(unique = true, nullable = false)
    @NotNull(message = "{user.name.notNull}")
    @NotEmpty(message = "{user.name.notEmpty}")
    private String name;

    @NotNull(message = "{user.password.notNull}")
    @NotEmpty(message = "{user.password.notEmpty}")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    private Client client;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
