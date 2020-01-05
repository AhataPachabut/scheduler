package com.it.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ResourceRequestDto {

    @NotNull(message = "{resource.name.notNull}")
    @NotEmpty(message = "{resource.name.notEmpty}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
