package com.service;

import com.repository.ICrudRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudService<T> {
    private final ICrudRepository<T> repository;

    protected AbstractCrudService(ICrudRepository<T> repository) {
        this.repository = repository;
    }

    public void save(T target) {
        repository.save(target);
    }

    protected ICrudRepository<T> getRepository() {
        return repository;
    }

    public void update(T target) {
        repository.update(target);
    }

    public Optional<T> findById(String id) {
        return repository.findById(id);
    }

    public List<T> findAll() {
        return repository.getAll();
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public void printAll() {
        for (T target : repository.getAll()) {
            System.out.println(target);
        }
    }
}
