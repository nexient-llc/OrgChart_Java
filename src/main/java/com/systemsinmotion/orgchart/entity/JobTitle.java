package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "JOBTITLE")
public class JobTitle extends BaseEntity{
	
	@Column(name = "NAME", nullable = false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
