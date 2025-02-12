package com.java._4.base;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T extends BaseEntity, ID, R extends BaseRepository<T, ID>> {
    protected final BaseRepository<T, ID> repository;

    protected BaseService(R repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return repository.findByDeletedFalse();
    }

    public Optional<T> findById(ID id) {
        return repository.findByIdAndDeletedFalse(id);
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void deleteById(ID id) {
        Optional<T> entityOpt = repository.findById(id);
        entityOpt.ifPresent(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
        });
    }
}
