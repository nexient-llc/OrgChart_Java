package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.systemsinmotion.orgchart.entity.BaseEntity;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
	private Department department;

	@Column(name = "EMAIL")
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 90)
	private String email;
	
	@Column(name = "FIRST_NAME")
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String firstName;
	
	@Column(name="IS_MANAGER")
	private Boolean isManager;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JOB_TITLE_ID", referencedColumnName = "ID")
	private JobTitle jobTitle;
	
	@Column(name = "LAST_NAME")
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String lastName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	private Employee manager;
	
	@Column(name="MIDDLE_INITIAL")
	private Character middleInitial;

	@Column(name = "SKYPE_NAME")
	@Size(min = 1, max = 90)
	@NotNull
	private String skypeName;
	
	public Department getDepartment(){
		return this.department;
	}

	public String getEmail(){
		return this.email;
	}

	public String getFirstName(){
		return this.firstName;
	}

	public Boolean getIsManager(){
		return this.isManager;
	}

	public JobTitle getJobTitle(){
		return this.jobTitle;
	}

	public String getLastName(){
		return this.lastName;
	}	
	
	public Employee getManager() {
		return this.manager;
	}

	public Character getMiddleInitial(){
		return this.middleInitial;
	}		
	
	public String getSkypeName() {
		return this.skypeName;
	}

	public void setDepartment(Department department){
		this.department = department;
	}

	public void setEmail(String email){
		this.email = email;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public void setIsManager(Boolean isManager){
		this.isManager = isManager;
	}

	public void setJobTitle(JobTitle jobTitle){
		this.jobTitle= jobTitle;
	}	
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public void setManager(Employee manager){
		this.manager= manager;
	}
	
	public void setMiddleInitial(Character middleInitial){
		this.middleInitial = middleInitial;
	}

	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}
}