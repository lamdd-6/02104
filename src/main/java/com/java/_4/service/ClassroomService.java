package com.java._4.service;

import com.java._4.model.Classroom;
import com.java._4.base.BaseService;
import com.java._4.repository.ClassroomRepo;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService extends BaseService<Classroom, String, ClassroomRepo> {
    public ClassroomService(ClassroomRepo repository) {
        super(repository);
    }
}
