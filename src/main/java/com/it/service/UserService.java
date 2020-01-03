package com.it.service;

import com.it.model.User;

public interface UserService extends GenericService<User, Long> {

    User findByName(String name);

}
