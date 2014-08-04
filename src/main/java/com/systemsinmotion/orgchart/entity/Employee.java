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
	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 20)
	private String firstName;
	
	@Column(name = "LAST_NAME", nullable = false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	private String lastName;

	@Column(name = "MIDDLE_INITIAL", length = 1)
	private Character middleInitial;
	
	@Column(name = "EMAIL", nullable = true, length = 100)
	@Size(min = 0, max = 100)
	private String email;
	
	@Column(name = "SKYPE_NAME", nullable = true, length = 100)
	@Size(min = 0, max = 100)
	private String skypeName;

	@Column(name = "IS_MANAGER", nullable = true)
	private boolean isManager;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID", nullable=false)
	private Department department;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	private Employee manager;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JOB_TITLE_ID", referencedColumnName = "ID")
	private JobTitle jobTitle;
	
	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Character getMiddleInitial() {
		return this.middleInitial;
	}
	
	public String getEmail() {		
		return this.email;
	}
	
	public String getSkypeName() {		
		return this.skypeName;
	}
	
	public boolean getIsManager() {
		return this.isManager;
	}
	
	public Department getDepartment() {
		return this.department;
	}
	
	public Employee getManager() {
		return this.manager;
	}
	
	public JobTitle getJobTitle() {		
		return this.jobTitle;
	}
	
	public void setFirstName(String firstName) {
		this.firstName= firstName;		
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;		
	}

	public void setMiddleInitial(Character middleInitial) {
		this.middleInitial= middleInitial;		
	}
	
	public void setEmail(String email) {
		this.email= email;		
	}
	
	public void setSkypeName(String skypeName) {
		this.skypeName= skypeName;		
	}
	
	public void setIsManager(boolean isManager) {
		this.isManager=isManager;
	}
	
	public void setDepartment(Department department) {
		this.department= department;
	}	

	public void setManager(Employee manager) {
		this.manager=manager;		
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle=jobTitle;	
	}
	@Override
	public String toString(){
		if(this.middleInitial==null)
		return this.firstName+" "+this.lastName;
		return this.firstName+" "+this.middleInitial+" "+this.lastName;
	}

	public String toJson() {
		return super.toString();
	}
	
}
