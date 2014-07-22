package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class JobTitle extends BaseEntity {

	@Column(name = "NAME", nullable = false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 15)
	private String name;
	
	public void setName(String newName) {
		this.name = newName;
	}

	public String getName() {
		return this.name;
	}

}
