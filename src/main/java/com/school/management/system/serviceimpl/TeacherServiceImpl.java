package com.school.management.system.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.management.system.entity.Teacher;
import com.school.management.system.exception.TeacherNotFoundException;
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
        Teacher savedTeacher = teacherRepository.save(teacher);
        TeacherResponse teacherResponse = teacherMapper.mapTeacherToTeacherResponse(savedTeacher);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseStructure<TeacherResponse>()
                        .setStatuscode(HttpStatus.CREATED.value())
                        .setMessage("Teacher added successfully")
                        .setData(teacherResponse));
    }

    @Override
    public ResponseEntity<ResponseStructure<TeacherResponse>> getTeacherById(int id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + id));

        TeacherResponse teacherResponse = teacherMapper.mapTeacherToTeacherResponse(teacher);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseStructure<TeacherResponse>()
                        .setStatuscode(HttpStatus.OK.value())
                        .setMessage("Teacher found")
                        .setData(teacherResponse));
    }

    @Override
    public ResponseEntity<ResponseStructure<TeacherResponse>> updateTeacher(int id, TeacherRequest teacherRequest) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + id));

        Teacher updatedTeacher = teacherMapper.mapTeacherRequestToTeacher(teacherRequest, teacher);
        Teacher savedTeacher = teacherRepository.save(updatedTeacher);
        TeacherResponse teacherResponse = teacherMapper.mapTeacherToTeacherResponse(savedTeacher);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseStructure<TeacherResponse>()
                        .setStatuscode(HttpStatus.OK.value())
                        .setMessage("Teacher updated successfully")
                        .setData(teacherResponse));
    }

    @Override
    public ResponseEntity<ResponseStructure<String>> deleteTeacher(int id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + id));

        teacherRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseStructure<String>()
                        .setStatuscode(HttpStatus.OK.value())
                        .setMessage("Teacher deleted successfully")
                        .setData("Deleted"));
    }
}

