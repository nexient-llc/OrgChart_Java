package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitle extends BaseEntity {

	private static final long serialVersionUID = -6905723931037952963L;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String name;
	
	private String description;
	
	@Column(name = "NAME", nullable = false, length = 50)
	public String getName(){
		return this.name;
	}
	
	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription(){
		return this.description;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
}
