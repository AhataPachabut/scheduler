package com.it.service;

import com.it.component.LocalizedMessageSource;
import com.it.component.Validator;
import com.it.model.Role;
import com.it.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * The type Role service.
 */
@Service
@Transactional
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private Validator validator;

    @Autowired
    private LocalizedMessageSource localizedMessageSource;

    @Override
    public Role findByName(String name) {
        return repository.findByName(name);
    }

    public Role save(Role entity) {
        validator.validate(repository.findById(entity.getId()) != null, localizedMessageSource.getMessage("role.id.Null", new Object[]{}));
        validator.validate(repository.findByName(entity.getName()) != null,localizedMessageSource.getMessage("role.name.NotUnique", new Object[]{}));

        return repository.save(entity);
    }

    public Role update(Role entity) {
        validator.validate(entity.getId() == null, localizedMessageSource.getMessage("role.id.NotNull", new Object[]{}));
        validator.validate(repository.findById(entity.getId()) == null,localizedMessageSource.getMessage("role.id.NotExist", new Object[]{}));
        final Role duplicateEntity = repository.findByName(entity.getName());
        validator.validate(duplicateEntity != null && !Objects.equals(duplicateEntity.getId(), entity.getId()), localizedMessageSource.getMessage("role.name.NotUnique", new Object[]{}));

        return repository.save(entity);
    }
}
