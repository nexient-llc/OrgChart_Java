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

	private static final long serialVersionUID = 2786300800604338231L;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String firstName;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String lastName;
	
	private String middleInitial;
	
	private String email;
	private String skypeName;

	private boolean isManager;
	private boolean isActive;
	
	private Department department;
	private JobTitle jobTitle;
	
	private Employee manager;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}	
	
	@Column(name = "FIRST_NAME", nullable = false, length = 45)
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "LAST_NAME", nullable = false, length = 45)
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "IS_MANAGER")
	public boolean getIsManager() {
		return this.isManager;
	}
	
	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}
	
	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "SKYPE_NAME")
	public String getSkypeName() {
		return this.skypeName;
	}
	
	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	public Employee getManager() {
		return this.manager;
	}
	
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	@Column(name = "MIDDLE_INITIAL", length = 45)
	public String getMiddleInitial() {
		return this.middleInitial;
	}
	
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JOB_TITLE_ID", referencedColumnName = "ID")
	public JobTitle getJobTitle() {
		return this.jobTitle;
	}
	
	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	@Column(name = "IS_ACTIVE")
	public boolean getIsActive() {
		return this.isActive;
	}
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
}