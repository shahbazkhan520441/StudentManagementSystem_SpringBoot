package com.school.management.system.serviceimpl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.management.system.entity.Course;
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
        courseRepository.save(course);
        CourseResponse courseResponse = courseMapper.mapCourseToCourseResponse(course);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseStructure<CourseResponse>()
                        .setStatuscode(HttpStatus.CREATED.value())
                        .setMessage("Course created successfully")
                        .setData(courseResponse));
    }

    @Override
    public ResponseEntity<ResponseStructure<CourseResponse>> getCourseById(int id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            CourseResponse courseResponse = courseMapper.mapCourseToCourseResponse(courseOptional.get());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseStructure<CourseResponse>()
                            .setStatuscode(HttpStatus.OK.value())
                            .setMessage("Course found")
                            .setData(courseResponse));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseStructure<CourseResponse>()
                            .setStatuscode(HttpStatus.NOT_FOUND.value())
                            .setMessage("Course not found")
                            .setData(null));
        }
    }

    @Override
    public ResponseEntity<ResponseStructure<CourseResponse>> updateCourse(int id, CourseRequest courseRequest) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            Course course = courseMapper.mapCourseRequestToCourse(courseRequest, courseOptional.get());
            courseRepository.save(course);
            CourseResponse courseResponse = courseMapper.mapCourseToCourseResponse(course);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseStructure<CourseResponse>()
                            .setStatuscode(HttpStatus.OK.value())
                            .setMessage("Course updated successfully")
                            .setData(courseResponse));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseStructure<CourseResponse>()
                            .setStatuscode(HttpStatus.NOT_FOUND.value())
                            .setMessage("Course not found")
                            .setData(null));
        }
    }

    @Override
    public ResponseEntity<ResponseStructure<String>> deleteCourse(int id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            courseRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseStructure<String>()
                            .setStatuscode(HttpStatus.OK.value())
                            .setMessage("Course deleted successfully")
                            .setData("Deleted"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseStructure<String>()
                            .setStatuscode(HttpStatus.NOT_FOUND.value())
                            .setMessage("Course not found")
                            .setData(null));
        }
    }
}
