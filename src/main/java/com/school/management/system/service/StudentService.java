package com.school.management.system.service;

import org.springframework.http.ResponseEntity;

import com.school.management.system.requestdto.StudentRequest;
import com.school.management.system.responsedto.StudentResponse;
import com.school.management.system.util.ResponseStructure;

public interface StudentService {

    ResponseEntity<ResponseStructure<StudentResponse>> addStudent(StudentRequest studentRequest);

    ResponseEntity<ResponseStructure<StudentResponse>> getStudentById(int id);

    ResponseEntity<ResponseStructure<StudentResponse>> updateStudent(int id, StudentRequest studentRequest);

    ResponseEntity<ResponseStructure<String>> deleteStudent(int id);
}
