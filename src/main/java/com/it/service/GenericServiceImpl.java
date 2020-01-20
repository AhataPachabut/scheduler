package com.it.service;

import com.it.component.Validator;
import com.it.model.BaseClass;
import com.it.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Generic service.
 *
 * @param <T> the type parameter
 * @param <U> the type parameter
 */
@Service
@Transactional
abstract class GenericServiceImpl<T extends BaseClass, U> implements GenericService<T, U> {

    @Autowired
    private GenericRepository<T, U> repository;

    @Autowired
    private Validator validator;

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findById(U id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public T save(T entity) {
        validator.validate(entity.getId() != null, "id.Null");

        return repository.save(entity);
    }

    public T update(T entity) {
        validator.validate(entity.getId() == null, "id.notNull");
        validator.validate(repository.findById((U) entity.getId()) == null,"id.notExist");

        return repository.save(entity);
    }

    public void delete(T entity) {
        repository.findById((U) entity.getId());
        repository.delete(entity);
    }

    public void deleteById(U id) {
        repository.findById(id);
        repository.deleteById(id);
    }
}
