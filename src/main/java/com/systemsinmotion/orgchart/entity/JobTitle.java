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

	@Column(name = "NAME", nullable = false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 49)
	private String name;
	
	public void setName(String newName) {
		this.name = newName;
	}

	public String getName() {
		return this.name;
	}

}
