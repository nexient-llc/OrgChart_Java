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

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {

	private static final long serialVersionUID = 2786300800604338231L;
	
	@NotNull
	@NotEmpty
	@Size(min = 1,max = 20)
	private String firstName;
	
	@NotNull
	@NotEmpty
	@Size(min = 1,max = 50)
	private String lastName;
	
	@Size(max=100)
	private String email;
	
	@Size(max=100)
	private String skypeName;
	
	private Department department;
	
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

	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	public void setJobTitle(JobTitle jobTitle){
		this.jobTitle = jobTitle;
	}
}

