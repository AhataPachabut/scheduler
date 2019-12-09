package com.it.dto;

import java.util.Set;

public class EventResponseDto {

    private String clientName;

    private String serviceName;

    private Set<String> resourcesName;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Set<String> getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(Set<String> resourcesName) {
        this.resourcesName = resourcesName;
    }
}
