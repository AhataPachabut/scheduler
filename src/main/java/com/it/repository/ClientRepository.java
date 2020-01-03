package com.it.repository;

import com.it.model.Client;

public interface ClientRepository extends GenericRepository<Client, Long> {

    Client findByName(String name);

}
