package com.school.management.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.school.management.system.requestdto.StudentRequest;
import com.school.management.system.responsedto.StudentResponse;
import com.school.management.system.service.StudentService;
import com.school.management.system.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<ResponseStructure<StudentResponse>> addStudent(@RequestBody @Valid StudentRequest studentRequest) {
        return studentService.addStudent(studentRequest);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<ResponseStructure<StudentResponse>> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<ResponseStructure<StudentResponse>> updateStudent(@PathVariable int id, @RequestBody @Valid StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteStudent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }
}
