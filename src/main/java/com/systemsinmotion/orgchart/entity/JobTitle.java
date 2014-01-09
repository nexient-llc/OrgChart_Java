package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity 
@Table(name = "JOB_TITLE")
public class JobTitle extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2589068245306192972L;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String Name;

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() 
	{
		return this.Name;
	}

	public void setName(String name) 
	{
		this.Name = name;		
	}

}
