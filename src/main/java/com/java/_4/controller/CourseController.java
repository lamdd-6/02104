package com.java._4.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.java._4.model.Course;
import com.java._4.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService service;
    private final ObjectMapper objectMapper;

    public CourseController(CourseService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable String id) {
        Optional<Course> course = service.findById(id);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return ResponseEntity.ok(service.save(course));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Course> partialUpdate(@PathVariable String id, @RequestBody Map<String, Object> updates) throws JsonMappingException {
        Optional<Course> existingCourse = service.findById(id);
        if (existingCourse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course course = existingCourse.get();
        objectMapper.updateValue(course, updates);

        return ResponseEntity.ok(service.save(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
