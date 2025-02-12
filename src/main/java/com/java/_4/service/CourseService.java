package com.java._4.service;

import com.java._4.model.Course;
import com.java._4.base.BaseService;
import com.java._4.repository.CourseRepo;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends BaseService<Course, String, CourseRepo> {
    public CourseService(CourseRepo repository) {
        super(repository);
    }
}
