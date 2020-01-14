package com.it.dto.response;

import java.util.Set;

/**
 * The type Event response dto.
 */
public class EventResponseDto {

    private Long id;

    private ClientResponseDto client;

    private ServiceResponseDto service;

    private Set<ResourceResponseDto> resources;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets client.
     *
     * @return the client
     */
    public ClientResponseDto getClient() {
        return client;
    }

    /**
     * Sets client.
     *
     * @param client the client
     */
    public void setClient(ClientResponseDto client) {
        this.client = client;
    }

    /**
     * Gets service.
     *
     * @return the service
     */
    public ServiceResponseDto getService() {
        return service;
    }

    /**
     * Sets service.
     *
     * @param service the service
     */
    public void setService(ServiceResponseDto service) {
        this.service = service;
    }

    /**
     * Gets resources.
     *
     * @return the resources
     */
    public Set<ResourceResponseDto> getResources() {
        return resources;
    }

    /**
     * Sets resources.
     *
     * @param resources the resources
     */
    public void setResources(Set<ResourceResponseDto> resources) {
        this.resources = resources;
    }
}
