package com.it.dto.response;

import com.it.dto.request.ClientRequestDto;

import java.util.Set;

/**
 * The type User response dto.
 */
public class UserResponseDto {

    private Long id;

    private String name;

    private Set<RoleResponseDto> roles;

    private ClientRequestDto client;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public Set<RoleResponseDto> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(Set<RoleResponseDto> roles) {
        this.roles = roles;
    }

    /**
     * Gets client.
     *
     * @return the client
     */
    public ClientRequestDto getClient() {
        return client;
    }

    /**
     * Sets client.
     *
     * @param client the client
     */
    public void setClient(ClientRequestDto client) {
        this.client = client;
    }
}
