package com.systemsinmotion.orgchart.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "DEPARTMENT")
public class Department extends BaseEntity {

	private static final long serialVersionUID = -5379179412533671591L;

	private Set<Department> childDepartments = new HashSet<Department>(0);
	private Set<Employee> employees = new HashSet<Employee>();
	private boolean isActive;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String name;

	private Department parentDepartment;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentDepartment")
	public Set<Department> getChildDepartments() {
		return this.childDepartments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}
	
	@Column(name = "IS_ACTIVE")
	public boolean getIsActive() {
		return this.isActive;
	}
	
	public void setIsActive(boolean isItActive) {
		this.isActive = isItActive;
	}

	@ManyToOne(fetch = FetchType.EAGER)//, cascade = CascadeType.ALL)
	@JoinColumn(name = "PARENT_DEPARTMENT_ID", referencedColumnName = "ID")
	public Department getParentDepartment() {
		return this.parentDepartment;
	}

	public void setChildDepartments(Set<Department> departments) {
		this.childDepartments = departments;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentDepartment(Department department) {
		this.parentDepartment = department;
	}

}