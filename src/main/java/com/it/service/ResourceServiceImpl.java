package com.it.service;

import com.it.model.Resource;
import com.it.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResourceServiceImpl extends GenericServiceImpl<Resource, Long> implements ResourceService {

    @Autowired
    private ResourceRepository repository;

    @Override
    public Resource findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Resource> findAllEmployee() {
        return repository.findAllEmployee();
    }

    @Override
    public List<Resource> findAllEquipment() {
        return repository.findAllEquipment();
    }


}
