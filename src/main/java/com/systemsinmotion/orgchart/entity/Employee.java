package com.systemsinmotion.orgchart.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {

	private static final long serialVersionUID = 2786300800604338231L;
	
	@NotNull
	@NotEmpty
	@Size(min = 1,max = 20)
	@Expose
	private String firstName;
	
	@NotNull
	@NotEmpty
	@Size(min = 1,max = 50)
	@Expose
	private String lastName;
	
	@Expose
	private Character middleInitial;
	
	@Size(max=100)
	@Expose
	private String email;
	
	@Size(max=100)
	@Expose
	private String skypeName;
	
	@Expose
	private boolean isManager = false;
	
	@Expose
	private boolean isActive = true;
	
	@Expose
	private Department department;
	
	@Expose
	private Employee manager;
	
	private JobTitle jobTitle;
	
	
	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirstName(){
		return this.firstName;
	}
	
	@Column(name = "LAST_NAME", nullable = false, length = 50)
	public String getLastName(){
		return this.lastName;
	}
	
	@Column(name = "MIDDLE_INITAL")
	public Character getMiddleInitial(){
		return this.middleInitial;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
	public Department getDepartment() {
		return department;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name = "JOB_TITLE_ID", referencedColumnName = "ID")
	public JobTitle getJobTitle() {
		return jobTitle;
	}
	
	@Column(name = "EMAIL", unique = true)
	public String getEmail() {
		return this.email;
	}
	
	@Column(name = "SKYPE_NAME", unique = true)
	public String getSkypeName() {
		return this.skypeName;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	public Employee getManager(){
		return this.manager;
	}
	
	@Column(name = "IS_MANAGER")
	public boolean getIsManager() {
		return this.isManager;
	}
	
	@Column(name = "IS_ACTIVE")
	public boolean getIsActive(){
		return this.isActive;
	}
	
	public void setSkypeName(String skypeName){
		this.skypeName = skypeName;
	}
	
	public void setEmail(String email){
		this.email = email;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public void setMiddleInitial(Character middleInitial){
		this.middleInitial = middleInitial;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	public void setJobTitle(JobTitle jobTitle){
		this.jobTitle = jobTitle;
	}

	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}
	
	public void setIsActive(boolean isActive){
		this.isActive = isActive;
	}
}

