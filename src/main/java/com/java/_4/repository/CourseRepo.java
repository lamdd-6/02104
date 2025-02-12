package com.java._4.repository;

import com.java._4.base.BaseRepository;
import com.java._4.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends BaseRepository<Course, String> {
}
