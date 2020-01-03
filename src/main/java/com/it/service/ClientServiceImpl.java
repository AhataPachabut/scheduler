package com.it.service;

import com.it.model.Client;
import com.it.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl extends GenericServiceImpl<Client, Long> implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public Client findByName(String name) {
        return repository.findByName(name);
    }

}
