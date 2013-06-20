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

//Creates an Entity (Table) for Employees

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements java.io.Serializable {
	
	// Serialization Seems Broken
	// Declare variables for Employee Table
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String skypeName;
	private Boolean isManager;
	private JobTitle jobTitleId;
	private Department departmentId;
	private Employee managerId;
	
	// Getters and Setters:
	
	// ID Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, unique = true )
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	// FIRST NAME Column
	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	// LAST NAME Column
	@Column(name = "LAST_NAME", nullable = false, length = 50)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	// EMAIL Column
	@Column(name = "EMAIL", nullable = true, length = 50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	// SKYPE NAME Column
	@Column(name = "SKYPE_NAME", nullable = true, length = 50)
	public String getSkypeName() {
		return skypeName;
	}
	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}
	
	// IS MANAGER Column
	@Column(name = "IS_MANAGER", nullable = true )
	public Boolean isManager() {
		return isManager;
	}
	public void setManager(Boolean isManager) {
		this.isManager = isManager;
	}
	
	// JOB TITLE ID Column
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JOB_TITLE_ID", referencedColumnName = "ID")
	public JobTitle getJobTitleId() {
		return jobTitleId;
	}
	public void setJobTitleId(JobTitle jobTitleId) {
		this.jobTitleId = jobTitleId;
	}
	
	// DEPARTMENT ID Column
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Department_ID", referencedColumnName ="ID")
	public Department getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Department departmentId) {
		this.departmentId = departmentId;
	}
	
	// MANAGER ID Column
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	public Employee getManagerId() {
		return managerId;
	}
	public void setManagerId(Employee managerId) {
		this.managerId = managerId;
	}

}
