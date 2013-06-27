package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

// Creates an Entity (Table) for Job Title
@Entity
@Table(name = "JOB_TITLE")
public class JobTitle implements java.io.Serializable {
	
	// Declare variables for Job Title Table
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String description;
	
	// Getters and Setters:
	// ID Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	// NAME Column
	@Column(name = "NAME", length = 50, nullable = false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 50, nullable = true)
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}	
}
