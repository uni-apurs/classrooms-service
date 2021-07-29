package com.apurs.microservices.classroomsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apurs.microservices.classroomsservice.dto.ClassroomCreateDTO;
import com.apurs.microservices.classroomsservice.dto.ClassroomDTO;
import com.apurs.microservices.classroomsservice.dto.ClassroomUpdateDTO;
import com.apurs.microservices.classroomsservice.service.ClassroomService;

@RequestMapping("/classrooms")
@RestController
public class ClassroomRestController {
	@Autowired
	private ClassroomService classroomService;
	
	@GetMapping("")
	public List<ClassroomDTO> getCourse(){
		return classroomService.findAll();
	}
	
	@GetMapping("/{id}")
	public ClassroomDTO getCourse(@PathVariable("id") Integer id) {
		return classroomService.findOne(id);
	}
	
	@PostMapping("")
	public ResponseEntity<ClassroomDTO> insertClassroom(@RequestBody ClassroomCreateDTO classroom) throws Exception {
		if (classroomService.insert(classroom) != null)
			return new ResponseEntity<>(HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("")
	public ResponseEntity<ClassroomDTO> updateClassroom(@RequestBody ClassroomUpdateDTO classroom) throws Exception {
		if (classroomService.update(classroom) != null)
			return new ResponseEntity<>(HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ClassroomDTO> deleteClassroom(@PathVariable ("id") Integer id) {
		if (classroomService.delete(id))
			return new ResponseEntity<>(HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/count")
	public ResponseEntity<String> countClassroomsByFacultyId(@RequestParam(required = false) Integer facultyId) {
		Integer count = classroomService.countByFacultyId(facultyId);
		if (count > 0)
			return new ResponseEntity<>("facultyId: " + facultyId + " | Classroom count: " + count, HttpStatus.OK);
					
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
