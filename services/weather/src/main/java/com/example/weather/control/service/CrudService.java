package com.example.weather.control.service;

import java.util.List;
import java.util.Optional;

/**
 * @param <T> Gives the Object type to
 * <p>
 * This interface defines the basic CRUD methods: create, read, update and delete
 * using save, findAll, findOne and delete
 * </p>
 */
public interface CrudService<T> {

    /**
     * Creates a <T> and adds it to the database
     *
     * @param entity a <T> to be added
     * @return the created <T>
     */
    T save (T entity);

    /**
     * Returns a specific <T> from the database by an ID
     *
     * @param id an ID for a <T>
     * @return the <T> found by the specified ID
     */
    Optional<T> findById(Long id);

    /**
     * Returns all <T> from the database
     *
     * @return the list of <T> currently in the database
     */
    List<T> findAll();

    /**
     * Deletes a specific <T> in the database by an ID
     *
     * @param id an ID for a <T>
     */
    void delete(Long id);
}
