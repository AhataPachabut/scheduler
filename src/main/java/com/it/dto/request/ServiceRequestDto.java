package com.it.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ServiceRequestDto {

    @NotNull(message = "{service.name.notNull}")
    @NotEmpty(message = "{service.name.notEmpty}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
