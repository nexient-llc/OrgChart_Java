package com.systemsinmotion.orgchart.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "Employee")

public class Employee extends BaseEntity {
	
	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 20)
	private String firstName;
	
	
	@Column(name = "LAST_NAME", nullable = false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	private String lastName;
	
	
	@Column(name = "MIDDLE_INITIAL", nullable = true, length = 1)
	private Character middleInitial;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	private Employee manager;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
	private Department department;
	
	
	@Column(name = "EMAIL", nullable = true, length = 50)
	@Size(min = 1, max = 50)
	private String email;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JOB_TITLE_ID", referencedColumnName = "ID")
	private JobTitle jobTitle;
	
	
	@Column(name = "SKYPE_NAME", nullable = true, length = 100)
	@Size(min = 1, max = 100)
	private String skypeName;
	
	
	//********* Getters and Setters for the various employee fields *********
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}
	
	
	public void setMiddleInitial(Character middleInitial) {
		this.middleInitial = middleInitial;
	}
	public Character getMiddleInitial() {
		return middleInitial;
	}
	
	
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public Employee getManager() {
		return manager;
	}
	
	
	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}
	public String getSkypeName() {
		return skypeName;
	}
	
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Department getDepartment()  {
		return department;
	}
	
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	
	
	public JobTitle getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getFullName() {
		return (middleInitial == null) ? (lastName + ", " + firstName) : (lastName + ", " + firstName + " " + middleInitial.toString() + ".");
	}
}
