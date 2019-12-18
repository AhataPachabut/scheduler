package com.it.dto.request;

import java.util.Set;

public class EventRequestDto {

    private Long clientId;

    private Long serviceId;

    private Set<Long> resourcesId;

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
