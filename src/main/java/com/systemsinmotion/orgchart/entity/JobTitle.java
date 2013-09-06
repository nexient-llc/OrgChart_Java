package com.systemsinmotion.orgchart.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitle implements Serializable {
	
	private static final long serialVersionUID = -1900310580694044993L;
	
	@NotNull
	@NotEmpty
	private String name;
	private Integer id;
	private Set<Employee> employees = new HashSet<Employee>(0);
	
	@OneToMany(mappedBy = "jobTitle", fetch = FetchType.LAZY)
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer jobTitleId) {
		this.id = jobTitleId;
	}
	
	@Column(name = "NAME", unique = true, nullable = false)
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
