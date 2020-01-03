package com.it.service;

import com.it.model.User;
import com.it.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public User save(User entity) {
        validate(entity.getId() != null, "id.Null");
        validate(repository.findByName(entity.getName()) != null,"name.notUnique");

        return repository.save(entity);
    }

    @Override
    public User update(User entity) {
        validate(entity.getId() == null, "id.notNull");
        validate(repository.findById(entity.getId()) == null,"id.notExist");
        final User duplicateEntity = repository.findByName(entity.getName());
        validate(duplicateEntity != null && !Objects.equals(duplicateEntity.getId(), entity.getId()),"name.notUnique");

        return repository.save(entity);
    }
}
