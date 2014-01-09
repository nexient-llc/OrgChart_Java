package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {

	private static final long serialVersionUID = 2786300800604338231L;
	
	private Department department;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 20)
	private String firstName;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	private String lastName;

	@Column(name = "DEPARTMENT_ID")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setFirstName(String newFirstName) {
		this.firstName = newFirstName;
		
	}

	public void setEmail(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setSkypeName(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setIsManager(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void setLastName(String newLastName) {
		this.lastName = newLastName;
		
	}
	
	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 50)
	public String getLastName() {
		return this.lastName;
	}

}
