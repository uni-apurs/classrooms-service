package com.apurs.microservices.classroomsservice.service;

import java.util.List;

import com.apurs.microservices.classroomsservice.dto.ClassroomCreateDTO;
import com.apurs.microservices.classroomsservice.dto.ClassroomDTO;
import com.apurs.microservices.classroomsservice.dto.ClassroomUpdateDTO;

public interface ClassroomService 
 {
	public abstract List<ClassroomDTO> findAll();
	public abstract ClassroomDTO findOne(Integer id);
	public abstract ClassroomDTO insert(ClassroomCreateDTO classroom) throws Exception;
	public abstract ClassroomDTO update(ClassroomUpdateDTO classroom) throws Exception;
	public abstract boolean delete(Integer id);
	
	public abstract Integer countByFacultyId(Integer facultyId);
}
