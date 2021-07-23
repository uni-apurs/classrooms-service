package com.apurs.microservices.classroomsservice.dto;

import java.time.ZonedDateTime;

public class ClassroomCreateDTO {
	private int facultyId;
	private String name;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
	
	public ClassroomCreateDTO(int facultyId, String name) {
		super();
		this.facultyId = facultyId;
		this.name = name;
		this.setCreatedAt(ZonedDateTime.now());
		this.setUpdatedAt(ZonedDateTime.now());
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public ZonedDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
