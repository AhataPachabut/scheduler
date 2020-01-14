package com.it.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The type Client request dto.
 */
public class ClientRequestDto {

    @NotNull(message = "{client.name.notNull}")
    @NotEmpty(message = "{client.name.notEmpty}")
    private String name;

    @NotNull(message = "{client.user.notNull}")
    private Long user;

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
     * Gets user.
     *
     * @return the user
     */
    public Long getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(Long user) {
        this.user = user;
    }
}
