package com.it.service;

import com.it.model.Resource;

import java.util.List;

/**
 * The interface Resource service.
 */
public interface ResourceService extends GenericService<Resource, Long> {

    /**
     * Find by name resource.
     *
     * @param name the name
     * @return the resource
     */
    Resource findByName(String name);

    /**
     * Find all employee list.
     *
     * @return the list
     */
    List<Resource> findAllEmployee();

    /**
     * Find all equipment list.
     *
     * @return the list
     */
    List<Resource> findAllEquipment();

}
