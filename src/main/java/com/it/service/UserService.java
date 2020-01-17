package com.it.service;

import com.it.model.User;

/**
 * The interface User service.
 */
public interface UserService extends GenericService<User, Long> {

    /**
     * Find by name user.
     *
     * @param name the name
     * @return the user
     */
    User findByName(String name);

}
