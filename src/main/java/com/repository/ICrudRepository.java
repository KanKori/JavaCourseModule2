package com.repository;

import java.util.List;
import java.util.Optional;

public interface ICrudRepository<T> {
    void save(T object);

    void saveAll(List<T> objects);

    List<T> getAll();

    Optional<T> findById(String id);

    boolean update(T object);

    boolean delete(String id);
}
