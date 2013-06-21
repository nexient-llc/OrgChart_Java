package com.systemsinmotion.orgchart.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

//Creates an Entity (Table) for Department

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
	private Set<Department> departments = new HashSet<Department>(0);
	private Employee managerId;
	
	
	// Getters and Setters
	
	// PARENT DEPARTMENT Column
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_DEPARTMENT_ID", referencedColumnName = "ID")
	public Department getParentDepartment() {
		return this.parentDepartment;
	}
	
	public void setParentDepartment(Department department) {
		this.parentDepartment = department;
	}
	
	// DEPARTMENTS Column
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentDepartment")
	public Set<Department> getDepartments() {
		return this.departments;
	}
	
	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
	
	// ID Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer departmentId) {
		this.id = departmentId;
	}
	
	// NAME Column
	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// Manager ID Column
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	public Employee getManagerId() {
		return managerId;
	}

	public void setManagerId(Employee managerId) {
		this.managerId = managerId;
	}

	


}