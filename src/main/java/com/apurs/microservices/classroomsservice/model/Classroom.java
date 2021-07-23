package com.apurs.microservices.classroomsservice.model;

import java.time.ZonedDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "\"classroom\"")
@SequenceGenerator(name = "classroom_id_seq", initialValue = 1, allocationSize = 1)
public class Classroom {
	@Id
	@Column(name = "\"id\"")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classroom_id_seq")
	private int id;
	
	@Column(name = "\"facultyId\"")
	private int facultyId;
	
	@Column(name = "\"name\"")
	private String name;
	
	@Column(name = "\"createdAt\"")
	private ZonedDateTime createdAt;
	
	@Column(name = "\"updatedAt\"")
	private ZonedDateTime updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
