package com.apurs.microservices.classroomsservice.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apurs.microservices.classroomsservice.dto.ClassroomCreateDTO;
import com.apurs.microservices.classroomsservice.dto.ClassroomDTO;
import com.apurs.microservices.classroomsservice.dto.ClassroomUpdateDTO;
import com.apurs.microservices.classroomsservice.model.Classroom;
import com.apurs.microservices.classroomsservice.repository.ClassroomRepository;

@Service
public class ClassroomServiceImpl implements ClassroomService{

	private ClassroomRepository classroomRepository;
	
	private RestTemplate restTemplate = new RestTemplate();
	private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Value("${app.facultiesEndpoint}")
	private String facultiesEndpoint;
	
	public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
		this.classroomRepository = classroomRepository;
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	@Override
	public List<ClassroomDTO> findAll() {
		List<Classroom> classrooms = classroomRepository.findAll();
		List<ClassroomDTO> dtos = new ArrayList<ClassroomDTO>();
		
		for (Classroom classroom : classrooms)
			dtos.add(modelMapper.map(classroom, ClassroomDTO.class));
		
		return dtos;
	}

	@Override
	public ClassroomDTO findOne(Integer id) {
		Classroom classroom = classroomRepository.getById(id);
		return modelMapper.map(classroom, ClassroomDTO.class);
	}

	@Override
	public ClassroomDTO insert(ClassroomCreateDTO classroom) throws Exception {
		ResponseEntity<String> res = restTemplate.getForEntity(facultiesEndpoint + classroom.getFacultyId(), String.class);

		if (!res.getStatusCode().equals(HttpStatus.OK))
			throw new Exception("Invalid facultyId.");
		
		Classroom createClassroom = modelMapper.map(classroom, Classroom.class);
		createClassroom = classroomRepository.save(createClassroom);
		return modelMapper.map(createClassroom, ClassroomDTO.class);
	}

	@Override
	public ClassroomDTO update(ClassroomUpdateDTO classroom) throws Exception {
		if(!classroomRepository.existsById(classroom.getId()))
			return null;
		
		ResponseEntity<String> res = restTemplate.getForEntity(facultiesEndpoint + classroom.getFacultyId(), String.class);
		
		if (!res.getStatusCode().equals(HttpStatus.OK))
			throw new Exception("Invalid facultyId.");
		
		Classroom tempClassroom = classroomRepository.getById(classroom.getId());
		Classroom updatedClassroom = modelMapper.map(classroom, Classroom.class);
		updatedClassroom.setCreatedAt(tempClassroom.getCreatedAt());
		updatedClassroom = classroomRepository.save(updatedClassroom);
		return modelMapper.map(updatedClassroom, ClassroomDTO.class);
	}

	@Override
	public boolean delete(Integer id) {
		if(!classroomRepository.existsById(id))
			return false;
		
		classroomRepository.deleteById(id);
		return true;
	}
	
}
