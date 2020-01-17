package com.it.repository;

import com.it.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends GenericRepository<Role, Long> {

    Role findByName(String name);

}
