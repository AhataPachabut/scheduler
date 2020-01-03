package com.it.repository;

import com.it.model.Resource;

public interface ResourceRepository extends GenericRepository<Resource, Long> {

    Resource findByName(String name);

}
