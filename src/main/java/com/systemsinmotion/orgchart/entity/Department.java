package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

//An Entity (Table) for Department

@Entity
@Table(name = "DEPARTMENT")
public class Department implements java.io.Serializable {
	
	// Declare variables for Departments
	private static final long serialVersionUID = 1L; 
	private Integer id;
	private Department parentDepartment;
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String name;
	private Employee manager;
	
	
	// Getters and Setters
	
	// PARENT DEPARTMENT
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_DEPARTMENT_ID", referencedColumnName = "ID")
	public Department getParentDepartment() {
		return this.parentDepartment;
	}
	
	public void setParentDepartment(Department department) {
		this.parentDepartment = department;
	}
	
	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer departmentId) {
		this.id = departmentId;
	}
	
	// NAME
	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// Manager
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee managerId) {
		this.manager = managerId;
	}

}