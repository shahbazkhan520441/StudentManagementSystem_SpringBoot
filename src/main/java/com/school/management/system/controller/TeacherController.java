package com.school.management.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.school.management.system.requestdto.TeacherRequest;
import com.school.management.system.responsedto.TeacherResponse;
import com.school.management.system.service.TeacherService;
import com.school.management.system.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/teachers")
    public ResponseEntity<ResponseStructure<TeacherResponse>> addTeacher(@RequestBody @Valid TeacherRequest teacherRequest) {
        return teacherService.addTeacher(teacherRequest);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<ResponseStructure<TeacherResponse>> getTeacherById(@PathVariable int id) {
        return teacherService.getTeacherById(id);
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<ResponseStructure<TeacherResponse>> updateTeacher(@PathVariable int id, @RequestBody @Valid TeacherRequest teacherRequest) {
        return teacherService.updateTeacher(id, teacherRequest);
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteTeacher(@PathVariable int id) {
        return teacherService.deleteTeacher(id);
    }
}
