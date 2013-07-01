package com.systemsinmotion.orgchart.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




// An Entity (Table) for Employees

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements java.io.Serializable {
	
	// Declare variables for Employee Table
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String skypeName;
	private Boolean isManager;
	private JobTitle jobTitle;
	private Department department;
	private Employee manager;
	
	// Getters and Setters:
	
	// ID 
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
	public Boolean getIsManager() {
		return isManager;
	}
	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}
	
	// JOB TITLE
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn( name = "JOB_TITLE_ID", referencedColumnName = "ID")
	public JobTitle getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	// DEPARTMENT
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Department_ID", referencedColumnName ="ID")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	// MANAGER
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "MANAGER_ID", referencedColumnName ="ID")
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}

}
