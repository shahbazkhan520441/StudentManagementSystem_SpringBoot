package com.school.management.system.mapper;


import org.springframework.stereotype.Component;

import com.school.management.system.entity.Teacher;
import com.school.management.system.requestdto.TeacherRequest;
import com.school.management.system.responsedto.TeacherResponse;

@Component
public class TeacherMapper {

    public Teacher mapTeacherRequestToTeacher(TeacherRequest teacherRequest, Teacher teacher) {
        teacher.setTeacherName(teacherRequest.getTeacherName());
        teacher.setTeacherEmail(teacherRequest.getTeacherEmail());
        teacher.setTeacherAddress(teacherRequest.getTeacherAddress());
        return teacher;
    }

    public TeacherResponse mapTeacherToTeacherResponse(Teacher teacher) {
        return TeacherResponse.builder()
                .teacherId(teacher.getTeacherId())
                .teacherName(teacher.getTeacherName())
                .teacherEmail(teacher.getTeacherEmail())
                .teacherAddress(teacher.getTeacherAddress())
                .build();
    }
}
