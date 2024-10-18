package com.school.management.system.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.school.management.system.exception.CourseNotFoundException;
import com.school.management.system.exception.InvalidRequestException;
import com.school.management.system.exception.StudentNotFoundException;
import com.school.management.system.exception.TeacherNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status, String errorMessage, String rootCause) {
        return ResponseEntity.status(status)
                .body(new ErrorStructure<String>()
                        .setStatus(status.value())
                        .setMessage(errorMessage)
                        .setRootCause(rootCause));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handleStudentNotFoundException(StudentNotFoundException ex) {
        return errorResponse(HttpStatus.NOT_FOUND, "Student not found", ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handleTeacherNotFoundException(InvalidRequestException ex) {
        return errorResponse(HttpStatus.NOT_FOUND, "Teacher not found", ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handleCourseNotFoundException(CourseNotFoundException ex) {
        return errorResponse(HttpStatus.NOT_FOUND, "Course not found", ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handleCourseNotFoundException(Exception ex) {
        return errorResponse(HttpStatus.NOT_FOUND, "Course not found", ex.getMessage());
    }

   
}
