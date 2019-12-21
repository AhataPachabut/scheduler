package com.it.dto.response;

import com.it.dto.request.ClientRequestDto;

import java.util.Set;

public class UserResponseDto {

    private Long id;

    private String name;

    private Set<String> roles;

    private ClientRequestDto client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public ClientRequestDto getClient() {
        return client;
    }

    public void setClient(ClientRequestDto client) {
        this.client = client;
    }
}
