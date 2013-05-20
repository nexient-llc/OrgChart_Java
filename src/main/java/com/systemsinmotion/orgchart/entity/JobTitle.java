package com.systemsinmotion.orgchart.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * JobTitle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JOBTITLE")
public class JobTitle implements java.io.Serializable {

	private Integer id;
	private String name;

	// Constructors

	/** default constructor */
	public JobTitle() {
		
	}



	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getid() {
		return this.id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}