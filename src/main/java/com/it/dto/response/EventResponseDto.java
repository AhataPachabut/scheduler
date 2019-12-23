package com.it.dto.response;

import java.util.Set;

public class EventResponseDto {

    private Long id;

    private ClientResponseDto client;

    private ServiceResponseDto service;

    private Set<ResourceResponseDto> resources;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientResponseDto getClient() {
        return client;
    }

    public void setClient(ClientResponseDto client) {
        this.client = client;
    }

    public ServiceResponseDto getService() {
        return service;
    }

    public void setService(ServiceResponseDto service) {
        this.service = service;
    }

    public Set<ResourceResponseDto> getResources() {
        return resources;
    }

    public void setResources(Set<ResourceResponseDto> resources) {
        this.resources = resources;
    }
}
