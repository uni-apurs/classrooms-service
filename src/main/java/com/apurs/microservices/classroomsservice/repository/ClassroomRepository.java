package com.apurs.microservices.classroomsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apurs.microservices.classroomsservice.model.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
	List<Classroom> findByNameContainingIgnoreCase(String name);
	
	Integer countByFacultyId(Integer facultyId);
}
