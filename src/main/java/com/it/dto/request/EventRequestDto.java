package com.it.dto.request;

import java.util.Set;

public class EventRequestDto {

    private Long client;

    private Long service;

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
