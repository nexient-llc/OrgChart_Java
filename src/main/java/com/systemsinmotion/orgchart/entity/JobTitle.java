package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "job_title")
public class JobTitle extends BaseEntity{

	/**
	 * 
	 */
	@Column(name = "Name")
	private String Name;
	private static final long serialVersionUID = -6905723931037952963L;

	public String getName() {
		return this.Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

}
