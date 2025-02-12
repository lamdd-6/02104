package com.java._4.base;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends MongoRepository<T, ID> {
    List<T> findByDeletedFalse();
    Optional<T> findByIdAndDeletedFalse(ID id);
}
