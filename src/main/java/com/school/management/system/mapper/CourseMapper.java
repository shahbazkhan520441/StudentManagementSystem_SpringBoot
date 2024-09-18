package com.school.management.system.mapper;

import org.springframework.stereotype.Component;

import com.school.management.system.entity.Course;
import com.school.management.system.requestdto.CourseRequest;
import com.school.management.system.responsedto.CourseResponse;

@Component
public class CourseMapper {

    public Course mapCourseRequestToCourse(CourseRequest courseRequest, Course course) {
        course.setCourceName(courseRequest.getCourseName());
        course.setCourceDescription(courseRequest.getCourseDescription());
        return course;
    }

    public CourseResponse mapCourseToCourseResponse(Course course) {
        return CourseResponse.builder()
                .courseId(course.getCourceId())
                .courseName(course.getCourceName())
                .courseDescription(course.getCourceDescription())
                .build();
    }
}
