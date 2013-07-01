package com.systemsinmotion.orgchart.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table (name="TABLE")
public class Employee implements Serializable 
{
	private static final long serialVersionUID = 2786300800604338231L;
	
	@Id
	//@GeneratedValue (strategy= GenerationType.AUTO)
	private int Id;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	@NotNull
	private String email;
	
	@NotNull
	@Size(min = 1, max = 45)
	private String firstName;
	
	@NotNull
	@Size(min = 1, max = 45)
	private String lastName;
		
	private String middleInitial;
	
	@NotNull
	@Size(min = 1, max = 45)
	private String skypeName;
	
	@NotNull
	private Boolean isManager;

	private Department department;
	
	@NotNull
	private int dept_ID;
	
	@NotNull
	private String jobTitle;
	
	public Boolean getIsManager() {
		return isManager;
	}
	public void setIsManager(Boolean isManager) {
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
	public String getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSkypeName() {
		return skypeName;
	}
	public void setSkypeName(String skype) {
		this.skypeName = skype;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public int getDept_ID() {
		return dept_ID;
	}
	public void setDept_ID(int dept_ID) {
		this.dept_ID = dept_ID;
	}
	
}
