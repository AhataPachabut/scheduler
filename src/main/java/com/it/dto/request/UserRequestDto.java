package com.it.dto.request;


import javax.validation.constraints.NotNull;
import java.util.Set;

public class UserRequestDto {

    private String name;

    private String password;

    @NotNull(message = "{user.roles.notNull}")
    private Set<Long> roles;

    private Long client;

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

    public Set<Long> getRoles() {
        return roles;
    }

    public void setRoles(Set<Long> roles) {
        this.roles = roles;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) { this.client = client; }
}
