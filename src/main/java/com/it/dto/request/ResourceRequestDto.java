package com.it.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The type Resource request dto.
 */
public class ResourceRequestDto {

    @NotNull(message = "{resource.name.notNull}")
    @NotEmpty(message = "{resource.name.notEmpty}")
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
