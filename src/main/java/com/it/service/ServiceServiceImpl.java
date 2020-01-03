package com.it.service;

import com.it.model.Service;
import com.it.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class ServiceServiceImpl extends GenericServiceImpl<Service, Long> implements ServiceService {

    @Autowired
    private ServiceRepository repository;

    @Override
    public Service findByName(String name) {
        return repository.findByName(name);
    }

}
