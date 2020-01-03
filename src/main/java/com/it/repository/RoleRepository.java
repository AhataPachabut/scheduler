package com.it.repository;

import com.it.model.Role;

public interface RoleRepository extends GenericRepository<Role, Long> {

    Role findByName(String name);

}
