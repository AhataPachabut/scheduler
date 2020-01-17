package com.it.service;

import com.it.model.Service;

/**
 * The interface Service service.
 */
public interface ServiceService extends GenericService<Service, Long> {

    /**
     * Find by name service.
     *
     * @param name the name
     * @return the service
     */
    Service findByName(String name);

}
