package com.systemsinmotion.orgchart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="JOB_TITLE")
public class JobTitle implements Serializable
{
	private static final long serialVersionUID = -1081602605359641688L;
	
	
	private Integer id;

	private String Name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="NAME")
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
