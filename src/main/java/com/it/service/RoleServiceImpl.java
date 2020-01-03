package com.it.service;

import com.it.model.Role;
import com.it.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public Role findByName(String name) {
        return repository.findByName(name);
    }

    public Role save(Role entity) {
        validate(repository.findById(entity.getId()) != null,"id.Null");
        validate(repository.findByName(entity.getName()) != null,"name.notUnique");

        return repository.save(entity);
    }

    public Role update(Role entity) {
        validate(entity.getId() == null, "id.notNull");
        validate(repository.findById(entity.getId()) == null,"id.notExist");
        final Role duplicateEntity = repository.findByName(entity.getName());
        validate(duplicateEntity != null && !Objects.equals(duplicateEntity.getId(), entity.getId()),"name.notUnique");

        return repository.save(entity);
    }
}
