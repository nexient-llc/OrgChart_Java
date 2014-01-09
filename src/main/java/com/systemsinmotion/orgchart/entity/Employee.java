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
	@Size(min = 1, max = 45)
	private String firstName;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String lastName;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String email;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String skypeName;
	
	private Boolean isManager;
	
	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirstName(){
		return this.firstName;
	}
	
	@Column(name = "LAST_NAME", nullable = false, length = 50)
	public String getLastName(){
		return this.lastName;
	}
	
	@Column(name = "EMAIL", nullable = false, length = 100)
	public String getEmail(){
		return this.email;
	}
	
	@Column(name = "SKYPE_NAME", nullable = false, length = 100)
	public String getSkypeName(){
		return this.skypeName;
	}
	
	@Column(name = "IS_MANAGER", )
	public Boolean getIsManager(){
		return this.isManager;
	}
	
	public Department getDepartment() {
		return department;
	}

	
	public void setFirstName(String name){
		this.firstName = name;
	}
	
	public void setLastName(String name){
		this.lastName = name;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setSkypeName(String skypeName){
		this.skypeName = skypeName;
	}
	
	public void setIsManager(Boolean bool){
		this.isManager = bool;
	}
	
	 void setDepartment(Department department) {
		this.department = department;
	}
	
	

}
