package com.school.management.system.mapper;

import org.springframework.stereotype.Component;

import com.school.management.system.entity.Student;
import com.school.management.system.requestdto.StudentRequest;
import com.school.management.system.responsedto.StudentResponse;

@Component
public class StudentMapper {

	
	public Student mapStudentRequestToStudent(StudentRequest studentRequest,Student student) {
		student.setStudentName(studentRequest.getStudentName());
		student.setStudentEmail(studentRequest.getStudentEmail());
		student.setStudentAddress(studentRequest.getStudentAddress());
		
		return student;
	}
	
	public StudentResponse mapStudentToStudentResponse(Student student) {
		return StudentResponse.builder()
				.studentId(student.getStudentId())
				.studentEmail(student.getStudentEmail())
	             .studentName(student.getStudentName())
	             .studentAddress(student.getStudentAddress())
	             .build();
		
	}
}
