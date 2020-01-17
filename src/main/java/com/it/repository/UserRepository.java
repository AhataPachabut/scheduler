package com.it.repository;

import com.it.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User, Long> {

    User findByName(String name);

}
