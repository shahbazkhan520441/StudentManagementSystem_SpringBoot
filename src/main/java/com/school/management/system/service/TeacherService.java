package com.school.management.system.service;

import org.springframework.http.ResponseEntity;

import com.school.management.system.requestdto.TeacherRequest;
import com.school.management.system.responsedto.TeacherResponse;
import com.school.management.system.util.ResponseStructure;

public interface TeacherService {

    ResponseEntity<ResponseStructure<TeacherResponse>> addTeacher(TeacherRequest teacherRequest);

    ResponseEntity<ResponseStructure<TeacherResponse>> getTeacherById(int id);

    ResponseEntity<ResponseStructure<TeacherResponse>> updateTeacher(int id, TeacherRequest teacherRequest);

    ResponseEntity<ResponseStructure<String>> deleteTeacher(int id);
}
