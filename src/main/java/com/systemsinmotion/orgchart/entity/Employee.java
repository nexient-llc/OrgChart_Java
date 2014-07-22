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
	private Character middleInitial; // TODO causes some errors in the EmployeeRepository tests

	@Column(name = "LAST_NAME", nullable = false, length = 20)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 15)
	private String lastName;
	
	@Column(name = "EMAIL", nullable = true, length = 100)
	@Size(min = 3, max = 100)
	private String email;
	
	@Column(name = "SKYPE_NAME", nullable = true, length = 100)
	@Size(min = 3, max = 100)
	private String skypeName;
	
	@Column(name = "IS_MANAGER")
	private boolean isManager;
	
	@JoinColumn(name = "JOB_TITLE_ID", referencedColumnName = "ID")
	private JobTitle jobTitle;

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
	
	// email
	public String getEmail() {
		return this.email;
	}
	
	// jobtitle
	public JobTitle getJobTitle() {
		return this.jobTitle;
	}
	
	// skype
	public String getSkypeName(){
		return this.skypeName;
	}
	
	// is manager
	public boolean getIsManager(){
		return this.isManager;
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

	// manager
	public void setManager(Employee manager) {
		this.manager = manager;
	}	
	
	// email
	public void setEmail(String address){
		this.email = address;
	}

	// jobtitle
	public void setJobTitle(JobTitle title){
		this.jobTitle = title;
	}
	
	// skype
	public void setSkypeName(String name){
		this.skypeName = name;
	}
	
	// is manager
	public void setIsManager(boolean isManager){
		this.isManager = isManager;
	}
}