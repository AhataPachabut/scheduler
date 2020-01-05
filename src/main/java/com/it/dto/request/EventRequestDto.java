package com.it.dto.request;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class EventRequestDto {

    @NotNull(message = "{event.client.notNull}")
    private Long client;

    @NotNull(message = "{event.service.notNull}")
    private Long service;

    @NotNull(message = "{event.resources.notNull}")
    private Set<Long> resources;

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Long getService() {
        return service;
    }

    public void setService(Long service) {
        this.service = service;
    }

    public Set<Long> getResources() {
        return resources;
    }

    public void setResources(Set<Long> resources) {
        this.resources = resources;
    }
}
