package com.school.management.system.serviceimpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.management.system.entity.Course;
import com.school.management.system.exception.CourseNotFoundException;
import com.school.management.system.mapper.CourseMapper;
import com.school.management.system.repository.CourseRepository;
import com.school.management.system.requestdto.CourseRequest;
import com.school.management.system.responsedto.CourseResponse;
import com.school.management.system.service.CourseService;
import com.school.management.system.util.ResponseStructure;
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseMapper courseMapper;

	@Override
	public ResponseEntity<ResponseStructure<CourseResponse>> addCourse(CourseRequest courseRequest) {
		Course course = courseMapper.mapCourseRequestToCourse(courseRequest, new Course());
		Course savedCourse = courseRepository.save(course);
		CourseResponse courseResponse = courseMapper.mapCourseToCourseResponse(savedCourse);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<CourseResponse>()
						.setStatuscode(HttpStatus.CREATED.value())
						.setMessage("Course created successfully")
						.setData(courseResponse));
	}

	@Override
	public ResponseEntity<ResponseStructure<CourseResponse>> getCourseById(int id) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));

		CourseResponse courseResponse = courseMapper.mapCourseToCourseResponse(course);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseStructure<CourseResponse>()
						.setStatuscode(HttpStatus.OK.value())
						.setMessage("Course found")
						.setData(courseResponse));
	}

	@Override
	public ResponseEntity<ResponseStructure<CourseResponse>> updateCourse(int id, CourseRequest courseRequest) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));

		Course updatedCourse = courseMapper.mapCourseRequestToCourse(courseRequest, course);
		Course savedCourse = courseRepository.save(updatedCourse);
		CourseResponse courseResponse = courseMapper.mapCourseToCourseResponse(savedCourse);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseStructure<CourseResponse>()
						.setStatuscode(HttpStatus.OK.value())
						.setMessage("Course updated successfully")
						.setData(courseResponse));
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteCourse(int id) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));

		courseRepository.deleteById(id);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseStructure<String>()
						.setStatuscode(HttpStatus.OK.value())
						.setMessage("Course deleted successfully")
						.setData("Deleted"));
	}
}
