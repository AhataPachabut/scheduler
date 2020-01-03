package com.it.service;

import com.it.model.Resource;

public interface ResourceService extends GenericService<Resource, Long> {

    Resource findByName(String name);

}
