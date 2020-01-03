package com.it.service;

import com.it.model.BaseClass;
import com.it.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
abstract class GenericServiceImpl<T extends BaseClass, U> implements GenericService<T, U> {

    @Autowired
    private GenericRepository<T, U> repository;

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findById(U id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public T save(T entity) {
        validate(entity.getId() != null, "id.Null");

        return repository.save(entity);
    }

    public T update(T entity) {
        validate(entity.getId() == null, "id.notNull");
        validate(repository.findById((U) entity.getId()) == null,"id.notExist");

        return repository.save(entity);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public void deleteById(U id) {
        repository.deleteById(id);
    }

    protected void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
