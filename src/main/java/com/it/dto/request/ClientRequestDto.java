package com.it.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClientRequestDto {

    @NotNull(message = "{client.name.notNull}")
    @NotEmpty(message = "{client.name.notEmpty}")
    private String name;

    private Long user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
