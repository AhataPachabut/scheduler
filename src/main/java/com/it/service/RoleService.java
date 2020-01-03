package com.it.service;

import com.it.model.Role;

public interface RoleService extends GenericService<Role, Long> {

    Role findByName(String name);

}
