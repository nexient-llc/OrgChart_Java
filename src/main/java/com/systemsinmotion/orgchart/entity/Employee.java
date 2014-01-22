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
	
	private Department department;
	private JobTitle jobTitle;
	private Employee manager;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String firstName;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String lastName;
	
	@Size(min = 1, max = 45)
	private String email;
	
	@Size(min = 1, max = 45)
	private String skypeName;

	@Size(min = 0, max = 1)
	private String middleInitial;
	
	private Boolean isManager = false;
	private Boolean isActive = true;
	
	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirstName(){
		return this.firstName;
	}
	
	@Column(name = "LAST_NAME", nullable = false, length = 50)
	public String getLastName(){
		return this.lastName;
	}
	
	@Column(name = "MIDDLE_INITIAL", nullable = false, length = 1)
	public String getMiddleInitial(){
		return this.middleInitial;
	}
	
	@Column(name = "EMAIL", nullable = false, length = 100)
	public String getEmail(){
		return this.email;
	}
	
	@Column(name = "SKYPE_NAME", nullable = false, length = 100)
	public String getSkypeName(){
		return this.skypeName;
	}
	
	@Column(name = "IS_MANAGER")
	public Boolean getIsManager(){
		return this.isManager;
	}
	
	@Column(name = "IS_ACTIVE")
	public Boolean getIsActive(){
		return this.isActive;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JOB_TITLE_ID", referencedColumnName = "ID")
	public JobTitle getJobTitle() {
		return jobTitle;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
	public Department getDepartment() {
		return department;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	public Employee getManager(){
		return manager;
	}
	
	public void setFirstName(String name){
		this.firstName = name;
	}
	
	public void setLastName(String name){
		this.lastName = name;
	}
	
	public void setMiddleInitial(String name){
		this.middleInitial = name;
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
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public void setJobTitle(JobTitle jobTitle){
		this.jobTitle = jobTitle;
	}
	
	public void setManager(Employee manager){
		this.manager = manager;
	}
	
	public void setIsActive(Boolean bool){
		this.isActive = bool;
	}

}
