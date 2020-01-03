package com.it.repository;

import com.it.model.User;

public interface UserRepository extends GenericRepository<User, Long> {

    User findByName(String name);

}
