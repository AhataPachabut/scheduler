package com.it.repository;

import com.it.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends GenericRepository<Client, Long> {

    Client findByName(String name);

}
