package com.it.dto;

import java.util.Set;

public class EventResponseDto {

    private Long id;

    private Long clientId;

    private Long serviceId;

    private Set<Long> resourcesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Set<Long> getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Set<Long> resourcesId) {
        this.resourcesId = resourcesId;
    }
}
