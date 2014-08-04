package com.systemsinmotion.orgchart.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitle extends BaseEntity {
	
	@Column(name = "NAME", nullable = false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	private String name;
	
	//********* Getters and setters *********
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
