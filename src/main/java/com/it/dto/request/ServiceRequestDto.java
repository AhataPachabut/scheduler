package com.it.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The type Service request dto.
 */
public class ServiceRequestDto {

    @NotNull(message = "{service.name.notNull}")
    @NotEmpty(message = "{service.name.notEmpty}")
    private String name;

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
}
