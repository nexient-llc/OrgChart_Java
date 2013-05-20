package com.systemsinmotion.orgchart.entity;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitle implements java.io.Serializable {

	private static final long serialVersionUID = 7594500043226838564L;
	
	private Integer id;
	private String name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	public Integer getId() {
		return id;
	}
	
	@NotEmpty
	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return name;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
