package com.it.service;

import com.it.model.Resource;

import java.util.List;

public interface ResourceService extends GenericService<Resource, Long> {

    Resource findByName(String name);

    List<Resource> findAllEmployee();

    List<Resource> findAllEquipment();

}
