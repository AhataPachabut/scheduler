package com.it.service;

import com.it.model.Client;

public interface ClientService extends GenericService<Client, Long> {

    Client findByName(String name);

}
