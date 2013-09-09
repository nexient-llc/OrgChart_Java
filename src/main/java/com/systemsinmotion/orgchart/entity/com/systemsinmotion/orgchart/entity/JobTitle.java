package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "JOB_TITLE")
public class JobTitle implements java.io.Serializable {
	private static final long serialVersionUID = -7585641524151978244L;
	private Integer id;
	private String name;
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "ID", nullable = false, unique = true)
	public Integer getId() {
		return id;
	}
	
	@Column (name = "DESCRIPTION", nullable = false, unique = true, length = 50)
	public String getName() {
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
