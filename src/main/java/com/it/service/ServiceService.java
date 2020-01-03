package com.it.service;

import com.it.model.Service;

public interface ServiceService extends GenericService<Service, Long> {

    Service findByName(String name);

}
