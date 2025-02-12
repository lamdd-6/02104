package com.java._4.repository;

import com.java._4.model.Classroom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepo extends MongoRepository<Classroom, String> {
}
