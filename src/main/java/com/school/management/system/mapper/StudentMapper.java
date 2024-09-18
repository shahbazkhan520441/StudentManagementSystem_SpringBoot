package com.school.management.system.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.school.management.system.entity.Course;
import com.school.management.system.entity.Student;
import com.school.management.system.repository.CourseRepository;
import com.school.management.system.requestdto.StudentRequest;
import com.school.management.system.responsedto.StudentResponse;

@Component
public class StudentMapper {

    @Autowired
    private CourseRepository courseRepository;

    public Student mapStudentRequestToStudent(StudentRequest studentRequest, Student student) {
        student.setStudentName(studentRequest.getStudentName());
        student.setStudentEmail(studentRequest.getStudentEmail());
        student.setStudentAddress(studentRequest.getStudentAddress());

        // Map the courseIds to Course entities and associate them with the Student
        if (studentRequest.getCourseIds() != null) {
            List<Course> courses = new ArrayList<>();
            for (int courseId : studentRequest.getCourseIds()) {
                courseRepository.findById(courseId).ifPresent(courses::add);
            }
            student.setCourses(courses); // Set the courses for the student
        }
        return student;
    }

    public StudentResponse mapStudentToStudentResponse(Student student) {
        List<Integer> courseIds = new ArrayList<>();
        if (student.getCourses() != null) {
            for (Course course : student.getCourses()) {
                courseIds.add(course.getCourceId());
            }
        }

        return StudentResponse.builder()
                .studentId(student.getStudentId())
                .studentEmail(student.getStudentEmail())
                .studentName(student.getStudentName())
                .studentAddress(student.getStudentAddress())
                .courseIds(courseIds) // Include the course IDs in the response
                .build();
    }
}
