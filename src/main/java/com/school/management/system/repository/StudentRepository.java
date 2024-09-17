package com.school.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.system.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	

}
