package com.it.service;

import com.it.model.Client;

/**
 * The interface Client service.
 */
public interface ClientService extends GenericService<Client, Long> {

    /**
     * Find by name client.
     *
     * @param name the name
     * @return the client
     */
    Client findByName(String name);

}
