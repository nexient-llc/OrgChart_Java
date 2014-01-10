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

	private static final long serialVersionUID = 2786300800604338231L;
	
	
	private Department department;
	private Employee manager;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String firstName;
	
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String lastName;
	
	
	@Size(min = 1, max = 100)
	private String skypeName;
	
	
	@Size(min = 1, max = 100)
	private String email;
	
	private Boolean isManager;
	
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
	public Department getDepartment() {
		return this.department;
	}

	
	public void setDepartment(Department department) {
		this.department = department;
	}

	@ManyToOne
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	public Employee getManager() {
		return this.manager;
	}

	
	public void setManager(Employee newManager) {
		this.manager = newManager;
	}
	
	public void setFirstName(String newFirstName) {
		this.firstName = newFirstName;
		
	}
	
	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setLastName(String newLastName) {
		 this.lastName = newLastName;
		
	}
	
	@Column(name = "LAST_NAME", nullable = false, length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setSkypeName(String newSkypeName) {
		 this.skypeName = newSkypeName;
		
	}
	
	@Column(name = "SKYPE_NAME", unique = true, length = 100)
	public String getSkypeName() {
		return this.skypeName;
	}

	public void setIsManager(Boolean managerStatus) {
		this.isManager = managerStatus;
		
	}
	
	@Column(name = "IS_MANAGER")
	public Boolean getIsManager() {
		return this.isManager;
	}

	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	@Column(name = "EMAIL", unique = true, length = 100)
	public String getEmail() {
		return this.email;
	}
	
	

}
