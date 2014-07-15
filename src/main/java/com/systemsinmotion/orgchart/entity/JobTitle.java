package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitle extends BaseEntity {

	@Column(name = "name", nullable = false, length = 50)
	@Size(min = 1, max = 50)
	@NotNull
	private String name;
	
	public JobTitle() {
		super();
		name = "title";
	}
	public void setName(String title){
		this.name = title;
	}
	
	public String getName(){
		return name;
	}
}
