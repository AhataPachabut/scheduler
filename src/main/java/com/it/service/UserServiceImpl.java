package com.it.service;

import com.it.component.LocalizedMessageSource;
import com.it.component.Validator;
import com.it.model.User;
import com.it.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * The type User service.
 */
@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private Validator validator;

    @Autowired
    private LocalizedMessageSource localizedMessageSource;

    @Override
    public User findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public User save(User entity) {
        validator.validate(entity.getId() != null, localizedMessageSource.getMessage("user.id.Null", new Object[]{}));
        validator.validate(repository.findByName(entity.getName()) != null,localizedMessageSource.getMessage("user.name.NotUnique", new Object[]{}));

        return repository.save(entity);
    }

    @Override
    public User update(User entity) {
        validator.validate(entity.getId() == null, localizedMessageSource.getMessage("user.id.NotNull", new Object[]{}));
        validator.validate(repository.findById(entity.getId()) == null,localizedMessageSource.getMessage("user.id.NotExist", new Object[]{}));
        final User duplicateEntity = repository.findByName(entity.getName());
        validator.validate(duplicateEntity != null && !Objects.equals(duplicateEntity.getId(), entity.getId()), localizedMessageSource.getMessage("user.name.NotUnique", new Object[]{}));

        return repository.save(entity);
    }
}
