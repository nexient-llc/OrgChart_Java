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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JobTitle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JOB_TITLE")
public class JobTitle implements java.io.Serializable {

	private static final long serialVersionUID = 7169591785618986919L;

	private Integer jobTitleId;
	private String description;
	private Set<Employee> employees = new HashSet<Employee>(0);

	// Constructors

	/** default constructor */
	public JobTitle() {
	}

	public JobTitle(String description) {
		this.description = description;
	}

	/** full constructor */
	public JobTitle(String description, Set<Employee> employees) {
		this.description = description;
		this.employees = employees;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "JOB_TITLE_ID", unique = true, nullable = false)
	public Integer getJobTitleId() {
		return this.jobTitleId;
	}

	public void setJobTitleId(Integer jobTitleId) {
		this.jobTitleId = jobTitleId;
	}

	@Column(name = "DESCRIPTION", nullable = false, length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jobTitle")
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}