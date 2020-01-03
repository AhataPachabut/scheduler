package com.it.repository;

import com.it.model.Service;

public interface ServiceRepository extends GenericRepository<Service, Long> {

    Service findByName(String name);

}
