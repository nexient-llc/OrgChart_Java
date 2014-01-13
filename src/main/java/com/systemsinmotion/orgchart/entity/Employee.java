package com.systemsinmotion.orgchart.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {

	private static final long serialVersionUID = 2786300800604338231L;
	
	private Department department;
	private String firstName;
	private String lastName;
	private String email;
	private String skypeName;
	private Boolean b;
	public String getEmail() {
		return email;
	}

	public String getSkypeName() {
		return skypeName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;	
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		
	}

	public void setIsManager(boolean b) {
		this.b = b;
	}

	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
		
	}

	public void setEmail(String email) {
		// TODO Auto-generated method stub
		
	}
	
	

}
