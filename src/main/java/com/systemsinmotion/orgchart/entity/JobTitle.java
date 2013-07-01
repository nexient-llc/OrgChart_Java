package com.systemsinmotion.orgchart.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="JOBTITLE")
public class JobTitle implements Serializable
{
	private static final long serialVersionUID = -1081602605359641688L;
	
	private String Description;
	@Id
	private String Name;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	} 	
}
