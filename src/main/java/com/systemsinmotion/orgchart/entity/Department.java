package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "DEPARTMENT")
public class Department extends BaseEntity {

	private static final long serialVersionUID = -5379179412533671591L;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	private String name;
	
	private Department parentDepartment;
	private boolean isActive;

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_DEPARTMENT_ID", referencedColumnName = "ID")
	public Department getParentDepartment() {
		return this.parentDepartment;
	}

	public void setParentDepartment(Department department) {
		this.parentDepartment = department;
	}
	
	@Column(name = "IS_ACTIVE")
	public boolean getIsActive() {
		return this.isActive;
	}
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	/*		  */
	/* UNUSED */
	/*		  */
	
	/*
	private Set<Department> childDepartments = new HashSet<Department>(0);
	private Set<Employee> employees = new HashSet<Employee>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentDepartment")
	public Set<Department> getChildDepartments() {
		return this.childDepartments;
	}

	public void setChildDepartments(Set<Department> departments) {
		this.childDepartments = departments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	*/
}