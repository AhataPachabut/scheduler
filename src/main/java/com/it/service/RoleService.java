package com.it.service;

import com.it.model.Role;

/**
 * The interface Role service.
 */
public interface RoleService extends GenericService<Role, Long> {

    /**
     * Find by name role.
     *
     * @param name the name
     * @return the role
     */
    Role findByName(String name);

}
