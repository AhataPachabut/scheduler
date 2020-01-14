package com.it.dto.request;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * The type Event request dto.
 */
public class EventRequestDto {

    @NotNull(message = "{event.client.notNull}")
    private Long client;

    @NotNull(message = "{event.service.notNull}")
    private Long service;

    @NotNull(message = "{event.resources.notNull}")
    private Set<Long> resources;

    /**
     * Gets client.
     *
     * @return the client
     */
    public Long getClient() {
        return client;
    }

    /**
     * Sets client.
     *
     * @param client the client
     */
    public void setClient(Long client) {
        this.client = client;
    }

    /**
     * Gets service.
     *
     * @return the service
     */
    public Long getService() {
        return service;
    }

    /**
     * Sets service.
     *
     * @param service the service
     */
    public void setService(Long service) {
        this.service = service;
    }

    /**
     * Gets resources.
     *
     * @return the resources
     */
    public Set<Long> getResources() {
        return resources;
    }

    /**
     * Sets resources.
     *
     * @param resources the resources
     */
    public void setResources(Set<Long> resources) {
        this.resources = resources;
    }
}
