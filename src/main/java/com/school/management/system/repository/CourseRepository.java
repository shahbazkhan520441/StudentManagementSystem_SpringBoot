package com.school.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.management.system.entity.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
