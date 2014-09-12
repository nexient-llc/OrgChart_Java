package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitle extends BaseEntity {
	
	@Column(name = "NAME")
	@Size(min = 1, max = 90)	
	private String name;
	
	String getName(){
		return this.name;
	}
	
	void setName(String name){
		this.name = name;
	}
}
