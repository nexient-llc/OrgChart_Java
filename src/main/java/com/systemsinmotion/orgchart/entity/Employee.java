// Some Errors to Fix with class relationships/need to fix job/department/manager

package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements java.io.Serializable {
	
	// Serialization Seems Broken
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String skypeName;
	private boolean isManager;
	private JobTitle jobTitleId;
	private Department departmentId;
	private Employee managerId;
	
	// Getters and Setters:
	// ID
	// Need to Fix relationships
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, unique = true )
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	// FIRST NAME
	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	// LAST NAME
	@Column(name = "LAST_NAME", nullable = false, length = 50)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	// EMAIL
	@Column(name = "EMAIL", nullable = true, length = 50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	// SKYPE NAME
	@Column(name = "SKYPE_NAME", nullable = true, length = 50)
	public String getSkypeName() {
		return skypeName;
	}
	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}
	
	// IS MANAGER
	@Column(name = "IS_MANAGER", nullable = true )
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	
	// JOB TITLE
	public JobTitle getJobTitleId() {
		return jobTitleId;
	}
	public void setJobTitleId(JobTitle jobTitleId) {
		this.jobTitleId = jobTitleId;
	}
	
	// DEPARTMENT ID
	public Department getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Department departmentId) {
		this.departmentId = departmentId;
	}
	
	// MANAGER ID
	public Employee getManagerId() {
		return managerId;
	}
	public void setManagerId(Employee managerId) {
		this.managerId = managerId;
	}

}
