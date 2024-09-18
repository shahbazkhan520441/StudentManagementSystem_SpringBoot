package com.school.management.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.school.management.system.requestdto.CourseRequest;
import com.school.management.system.responsedto.CourseResponse;
import com.school.management.system.service.CourseService;
import com.school.management.system.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/courses")
    public ResponseEntity<ResponseStructure<CourseResponse>> addCourse(@RequestBody @Valid CourseRequest courseRequest) {
        return courseService.addCourse(courseRequest);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<ResponseStructure<CourseResponse>> getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<ResponseStructure<CourseResponse>> updateCourse(@PathVariable int id, @RequestBody @Valid CourseRequest courseRequest) {
        return courseService.updateCourse(id, courseRequest);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteCourse(@PathVariable int id) {
        return courseService.deleteCourse(id);
    }
}
