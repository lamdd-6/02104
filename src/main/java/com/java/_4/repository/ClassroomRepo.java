package com.java._4.repository;

import com.java._4.base.BaseRepository;
import com.java._4.model.Classroom;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepo extends BaseRepository<Classroom, String> {
}
