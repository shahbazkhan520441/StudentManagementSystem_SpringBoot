package com.school.management.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.system.requestdto.StudentRequest;
import com.school.management.system.responsedto.StudentResponse;
import com.school.management.system.service.StudentService;
import com.school.management.system.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class StudentController{
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/students")
	public ResponseEntity<ResponseStructure<StudentResponse>> addStudent(@RequestBody @Valid StudentRequest studentRequest){
		return  studentService.addStudent(studentRequest);
	}


    @GetMapping("/findstudent/{id}")
    public ResponseEntity<ResponseStructure<StudentResponse>> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/updatestudent/{id}")
    public ResponseEntity<ResponseStructure<StudentResponse>> updateStudent(@PathVariable int id, @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("/deletestudent/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteStudent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }
	
    @GetMapping("/test")
    public String get() {
    	
    	return "hello world";
    }

}
