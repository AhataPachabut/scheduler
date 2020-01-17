package com.it.service;

import java.util.List;

/**
 * The interface Generic service.
 *
 * @param <T> the type parameter
 * @param <U> the type parameter
 */
public interface GenericService<T,U> {

    /**
     * Find all list.
     *
     * @return the list
     */
    List<T> findAll();

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    T findById(U id);

    /**
     * Save t.
     *
     * @param entity the entity
     * @return the t
     * @throws Exception the exception
     */
    T save(T entity) throws Exception;

    /**
     * Update t.
     *
     * @param entity the entity
     * @return the t
     * @throws Exception the exception
     */
    T update(T entity) throws Exception;

    /**
     * Delete.
     *
     * @param entity the entity
     */
    void delete(T entity);

    /**
     * Delete by id.
     *
     * @param id the id
     */
    void deleteById(U id);

}
