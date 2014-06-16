package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {

	private static final long serialVersionUID = 2786300800604338231L;

	@ManyToOne
	@JoinColumn(name="DEPARTMENT_ID")
	private Department department;
	
	@ManyToOne
	@JoinColumn(name="JOB_TITLE_ID")
	private JobTitle jobtitle;
	
	@Column(name = "First_Name", nullable = false, length = 50)
	private String firstName;
	
	@Column(name = "Last_Name", nullable = false, length = 50)
	private String lastName;
	
	@Column(name = "Middle_Initial", nullable = true, length = 50)
	private Character middleInitial;
	
	@ManyToOne
	private Employee manager;
	
	@Column(name = "IsManager")
	private boolean isManager;
	
	@Column(name = "EmaiL", nullable = false, length = 50)
	private String email;
	
	@Column(name = "Skype_Name", nullable = false, length = 50)
	private String skypeName;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSkypeName() {
		return skypeName;
	}

	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}


	public boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Character getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(Character middleInitial) {
		this.middleInitial = middleInitial;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}	
	

}
