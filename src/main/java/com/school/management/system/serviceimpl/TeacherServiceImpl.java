package com.school.management.system.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.management.system.entity.Teacher;
import com.school.management.system.mapper.TeacherMapper;
import com.school.management.system.repository.TeacherRepository;
import com.school.management.system.requestdto.TeacherRequest;
import com.school.management.system.responsedto.TeacherResponse;
import com.school.management.system.service.TeacherService;
import com.school.management.system.util.ResponseStructure;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public ResponseEntity<ResponseStructure<TeacherResponse>> addTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = teacherMapper.mapTeacherRequestToTeacher(teacherRequest, new Teacher());
        teacherRepository.save(teacher);
        TeacherResponse teacherResponse = teacherMapper.mapTeacherToTeacherResponse(teacher);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseStructure<TeacherResponse>()
                        .setStatuscode(HttpStatus.CREATED.value())
                        .setMessage("Teacher added successfully")
                        .setData(teacherResponse));
    }

    @Override
    public ResponseEntity<ResponseStructure<TeacherResponse>> getTeacherById(int id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isPresent()) {
            TeacherResponse teacherResponse = teacherMapper.mapTeacherToTeacherResponse(teacherOptional.get());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseStructure<TeacherResponse>()
                            .setStatuscode(HttpStatus.OK.value())
                            .setMessage("Teacher found")
                            .setData(teacherResponse));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseStructure<TeacherResponse>()
                            .setStatuscode(HttpStatus.NOT_FOUND.value())
                            .setMessage("Teacher not found")
                            .setData(null));
        }
    }

    @Override
    public ResponseEntity<ResponseStructure<TeacherResponse>> updateTeacher(int id, TeacherRequest teacherRequest) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isPresent()) {
            Teacher teacher = teacherMapper.mapTeacherRequestToTeacher(teacherRequest, teacherOptional.get());
            teacherRepository.save(teacher);
            TeacherResponse teacherResponse = teacherMapper.mapTeacherToTeacherResponse(teacher);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseStructure<TeacherResponse>()
                            .setStatuscode(HttpStatus.OK.value())
                            .setMessage("Teacher updated successfully")
                            .setData(teacherResponse));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseStructure<TeacherResponse>()
                            .setStatuscode(HttpStatus.NOT_FOUND.value())
                            .setMessage("Teacher not found")
                            .setData(null));
        }
    }

    @Override
    public ResponseEntity<ResponseStructure<String>> deleteTeacher(int id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isPresent()) {
            teacherRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseStructure<String>()
                            .setStatuscode(HttpStatus.OK.value())
                            .setMessage("Teacher deleted successfully")
                            .setData("Deleted"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseStructure<String>()
                            .setStatuscode(HttpStatus.NOT_FOUND.value())
                            .setMessage("Teacher not found")
                            .setData(null));
        }
    }
}
