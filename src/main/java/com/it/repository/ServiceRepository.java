package com.it.repository;

import com.it.model.Service;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends GenericRepository<Service, Long> {

    Service findByName(String name);

}
