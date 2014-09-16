package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitle extends BaseEntity {
	
	@Column(name = "NAME")
	@Size(min = 1, max = 90)	
	@NotNull
	private String name;
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
