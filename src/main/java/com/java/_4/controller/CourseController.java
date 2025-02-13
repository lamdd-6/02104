package com.java._4.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java._4.common.response.ApiResponse;
import com.java._4.model.Course;
import com.java._4.service.CourseService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService service;
    private final ObjectMapper objectMapper;
    private final MessageSource messageSource;

    public CourseController(CourseService service, ObjectMapper objectMapper, MessageSource messageSource) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.messageSource = messageSource;
    }

    private Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAll() {
        List<Course> courses = service.findAll();
        String message = messageSource.getMessage("course.list_success", null, getLocale());
        return ResponseEntity.ok(ApiResponse.success(courses, message));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getById(@PathVariable String id) {
        Optional<Course> course = service.findById(id);
        if (course.isEmpty()) {
            String message = messageSource.getMessage("course.not_found", null, getLocale());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(message, "COURSE_NOT_FOUND", HttpStatus.NOT_FOUND));
        }
        String message = messageSource.getMessage("course.get_success", null, getLocale());
        return ResponseEntity.ok(ApiResponse.success(course.get(), message));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> create(@RequestBody Course course) {
        Course savedCourse = service.save(course);
        String message = messageSource.getMessage("course.create_success", null, getLocale());
        return ResponseEntity.ok(ApiResponse.success(savedCourse, message));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> partialUpdate(@PathVariable String id, @RequestBody Map<String, Object> updates) throws JsonMappingException {
        Optional<Course> existingCourse = service.findById(id);
        if (existingCourse.isEmpty()) {
            String message = messageSource.getMessage("course.not_found", null, getLocale());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(message, "COURSE_NOT_FOUND", HttpStatus.NOT_FOUND));
        }
        Course course = existingCourse.get();
        objectMapper.updateValue(course, updates);
        Course updatedCourse = service.save(course);

        String message = messageSource.getMessage("course.update_success", null, getLocale());
        return ResponseEntity.ok(ApiResponse.success(updatedCourse, message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        service.deleteById(id);
        String message = messageSource.getMessage("course.delete_success", null, getLocale());
        return ResponseEntity.ok(ApiResponse.success(null, message));
    }
}
