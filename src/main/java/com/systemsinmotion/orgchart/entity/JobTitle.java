package com.systemsinmotion.orgchart.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitle extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666268641733103897L;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	

}
