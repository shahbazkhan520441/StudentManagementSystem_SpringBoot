package com.school.management.system.service;

import org.springframework.http.ResponseEntity;

import com.school.management.system.requestdto.CourseRequest;
import com.school.management.system.responsedto.CourseResponse;
import com.school.management.system.util.ResponseStructure;

public interface CourseService {

    ResponseEntity<ResponseStructure<CourseResponse>> addCourse(CourseRequest courseRequest);

    ResponseEntity<ResponseStructure<CourseResponse>> getCourseById(int id);

    ResponseEntity<ResponseStructure<CourseResponse>> updateCourse(int id, CourseRequest courseRequest);

    ResponseEntity<ResponseStructure<String>> deleteCourse(int id);
}

