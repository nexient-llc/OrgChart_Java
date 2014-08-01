package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Size(min = 1, max = 19)
	private String firstName;
	
	@Column(name = "LAST_NAME", nullable = false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String lastName;
	
	@Column(name = "MIDDLE_INITIAL", length = 1)
	private Character midInitial;
	
	@Column(name = "IS_MANAGER")
	private Boolean isManager;

	@Column(name = "EMAIL", length = 100)
	@Size(min = 1, max = 99)
	private String email;
	
	@Column(name = "SKYPE_NAME", length = 100)
	@Size(min = 1, max = 99)
	private String skypeName;
	
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department department;
	
	@ManyToOne
	@JoinColumn(name = "MANAGER_ID")
	private Employee manager;
	
	@ManyToOne
	@JoinColumn(name = "JOB_TITLE_ID")
	private JobTitle jobTitle;

	public void setFirstName(String string) {
		this.firstName = string;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String string) {
		this.lastName = string;		
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setMiddleInitial(Character mid) {
		this.midInitial = mid;		
	}

	public Character getMiddleInitial() {
		return midInitial;
	}

	public void setIsManager(Boolean isManager)
	{
		this.isManager = isManager;
	}
	
	public Boolean getIsMananger()
	{
		return isManager;
	}
	
	public String getFullName() {
		String fullName = "";
		if (firstName != null)
		{
			fullName += firstName;
		}
		if (midInitial != null)
		{
			fullName += " " + midInitial;
		}
		if (lastName != null)
		{
			fullName += " " + lastName;
		}
		
		return fullName.trim();
	}

	public void setEmail(String string) {
		this.email = string;		
	}

	public void setSkypeName(String string) {
		this.skypeName = string;		
	}

	public void setManager(Employee mgr) {
		this.manager = mgr;		
	}

	public void setDepartment(Department department) {
		this.department = department;		
	}
	
	public void setJobTitle(JobTitle job) {
		this.jobTitle = job;
	}

	public String getEmail() {
		return email;
	}
	
	public String getSkypeName() {
		return skypeName;
	}

	public Employee getManager() {
		return manager;
	}

	public Department getDepartment() {
		return department;
	}

	public JobTitle getJobTitle() {
		return jobTitle;
	}		
}
