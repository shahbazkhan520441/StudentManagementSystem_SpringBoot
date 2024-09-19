package com.school.management.system.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.management.system.entity.Student;
import com.school.management.system.exception.StudentNotFoundException;
import com.school.management.system.mapper.StudentMapper;
import com.school.management.system.repository.StudentRepository;
import com.school.management.system.requestdto.StudentRequest;
import com.school.management.system.responsedto.StudentResponse;
import com.school.management.system.service.StudentService;
import com.school.management.system.util.ResponseStructure;

@Service
public class StudentServiceImp implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public ResponseEntity<ResponseStructure<StudentResponse>> addStudent(StudentRequest studentRequest) {
		Student student = studentMapper.mapStudentRequestToStudent(studentRequest, new Student());
		Student savedStudent = studentRepository.save(student);
		StudentResponse studentResponse = studentMapper.mapStudentToStudentResponse(savedStudent);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<StudentResponse>()
						.setStatuscode(HttpStatus.CREATED.value())
						.setMessage("Student saved successfully")
						.setData(studentResponse));
	}
	
//--------------------------------------------------------------------------------------------------------------------
	
	@Override
	public ResponseEntity<ResponseStructure<StudentResponse>> getStudentById(int id) {
		return studentRepository.findById(id).map(student -> {
			StudentResponse studentResponse = studentMapper.mapStudentToStudentResponse(student);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<StudentResponse>()
							.setStatuscode(HttpStatus.OK.value())
							.setMessage("Student found")
							.setData(studentResponse));
		}).orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
	}
	
//--------------------------------------------------------------------------------------------------------------------

	@Override
	public ResponseEntity<ResponseStructure<StudentResponse>> updateStudent(int id, StudentRequest studentRequest) {
		return studentRepository.findById(id).map(student -> {
			Student updatedStudent = studentMapper.mapStudentRequestToStudent(studentRequest, student);
			Student savedStudent = studentRepository.save(updatedStudent);
			StudentResponse studentResponse = studentMapper.mapStudentToStudentResponse(savedStudent);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<StudentResponse>()
							.setStatuscode(HttpStatus.OK.value())
							.setMessage("Student updated successfully")
							.setData(studentResponse));
		}).orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
	}

//--------------------------------------------------------------------------------------------------------------------
	
	@Override
	public ResponseEntity<ResponseStructure<String>> deleteStudent(int id) {
		return studentRepository.findById(id).map(student -> {
			studentRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<String>()
							.setStatuscode(HttpStatus.OK.value())
							.setMessage("Student deleted successfully")
							.setData("Deleted"));
		}).orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
	}
}
