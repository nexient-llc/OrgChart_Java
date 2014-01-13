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
	private Employee manager;
	private JobTitle jobTitle;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 20)
	private String firstName;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	private String lastName;
	
	private String email;
	private boolean isManager = false;
	
	public void setEmail( String newEmail){
		this.email = newEmail;
	}
	
	@Column(name = "EMAIL", length = 100)
	public String getEmail(){
		return this.email;
	}
	
	public void setIsManager( boolean isAManager){
		this.isManager = isAManager;
	}
	
	@Column(name = "IS_MANAGER")
	public boolean getIsManager(){
		return this.isManager;
	}
	
	public void setFirstName( String newName){
		this.firstName = newName;
	}

	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setLastName( String newName){
		this.lastName = newName;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 50)
	public String getLastName() {
		return this.lastName;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	public Employee getManager(){
		return this.manager;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JOB_TITLE_ID", referencedColumnName = "ID")
	public JobTitle getJobTitle() {
		return jobTitle;
	}
	
	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	
}
