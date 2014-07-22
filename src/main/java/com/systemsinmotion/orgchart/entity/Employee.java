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
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {

	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 15)
	private String firstName;

	@Column(name = "MIDDLE_INITIAL")
	@NotNull
	@NotEmpty
	private Character middleInitial;

	@Column(name = "LAST_NAME", nullable = false, length = 20)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 15)
	private String lastName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	private Employee manager;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
	private Department department;

	// Get Details About Employee

	// First Name
	public String getFirstName() {
		return this.firstName;
	}

	// Last Name
	public String getLastName() {
		return this.lastName;
	}

	// Middle Initial
	public Character getMiddleInitial() {
		return this.middleInitial;
	}

	// Department
	public Department getDepartment() {
		return this.department;
	}

	// Manager
	public Employee getManager() {
		return this.manager;
	}

	// Fill In Details About Employee

	// First Name
	public void setFirstName(String name) {
		this.firstName = name;
	}

	// Last Name
	public void setLastName(String name) {
		this.lastName = name;
	}

	// Middle Initial
	public void setMiddleInitial(Character initial) {
		this.middleInitial = initial;
	}

	// Department
	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}
}