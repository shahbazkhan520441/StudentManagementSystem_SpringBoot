package com.school.management.system.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.management.system.entity.Student;
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
		
		studentRepository.save(student);
		
		studentMapper.mapStudentToStudentResponse(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<StudentResponse>().setStatuscode(HttpStatus.FOUND.value()).setMessage("student saved sucessfully")
				.setData(studentMapper.mapStudentToStudentResponse(student)));
	}
		
		@Override
	    public ResponseEntity<ResponseStructure<StudentResponse>> getStudentById(int id) {
	        Optional<Student> studentOptional = studentRepository.findById(id);
	        if (studentOptional.isPresent()) {
	            StudentResponse studentResponse = studentMapper.mapStudentToStudentResponse(studentOptional.get());
	            return ResponseEntity.status(HttpStatus.OK)
	                    .body(new ResponseStructure<StudentResponse>()
	                            .setStatuscode(HttpStatus.OK.value())
	                            .setMessage("Student found")
	                            .setData(studentResponse));
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body(new ResponseStructure<StudentResponse>()
	                            .setStatuscode(HttpStatus.NOT_FOUND.value())
	                            .setMessage("Student not found")
	                            .setData(null));
	        }
	    }

	    @Override
	    public ResponseEntity<ResponseStructure<StudentResponse>> updateStudent(int id, StudentRequest studentRequest) {
	        Optional<Student> studentOptional = studentRepository.findById(id);
	        if (studentOptional.isPresent()) {
	            Student student = studentMapper.mapStudentRequestToStudent(studentRequest, studentOptional.get());
	            studentRepository.save(student);
	            StudentResponse studentResponse = studentMapper.mapStudentToStudentResponse(student);
	            
	            return ResponseEntity.status(HttpStatus.OK)
	                    .body(new ResponseStructure<StudentResponse>()
	                            .setStatuscode(HttpStatus.OK.value())
	                            .setMessage("Student updated successfully")
	                            .setData(studentResponse));
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body(new ResponseStructure<StudentResponse>()
	                            .setStatuscode(HttpStatus.NOT_FOUND.value())
	                            .setMessage("Student not found")
	                            .setData(null));
	        }
	    }

	    @Override
	    public ResponseEntity<ResponseStructure<String>> deleteStudent(int id) {
	        Optional<Student> studentOptional = studentRepository.findById(id);
	        if (studentOptional.isPresent()) {
	            studentRepository.deleteById(id);
	            return ResponseEntity.status(HttpStatus.OK)
	                    .body(new ResponseStructure<String>()
	                            .setStatuscode(HttpStatus.OK.value())
	                            .setMessage("Student deleted successfully")
	                            .setData("Deleted"));
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body(new ResponseStructure<String>()
	                            .setStatuscode(HttpStatus.NOT_FOUND.value())
	                            .setMessage("Student not found")
	                            .setData(null));
	        }
	}

}
