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

	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	private String firstName = "";

	@Column(name = "LAST_NAME", nullable = false, length = 50)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	private String lastName = "";

	@Column(name = "MIDDLE_INITIAL", nullable = false, length = 1)
	private Character middleInitial = ' ';
    
	@Column(name="EMAIL", nullable = false, length = 50, unique = true)
	private String email = "";
	
	@Column(name="SKYPE_NAME", nullable = false, length = 50, unique = true)
	private String skypeName = "";
	
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department department;

	@ManyToOne
	@JoinColumn(name = "JOB_TITLE_ID")
	private JobTitle jobTitle;
    
	@Column(name = "IS_MANAGER", columnDefinition = "boolean default false")
	private boolean is_manager;
	
	@ManyToOne
	@JoinColumn(name = "MANAGER_ID")
	private Employee manager;
	
	public void setFirstName(String employeeFirstName) {
      
		firstName = employeeFirstName;
	}

	public void setLastName(String employeeLastName) {
       lastName = employeeLastName;
	}

	public void setMiddleInitial(Character initial) {
      middleInitial = initial;
	}

	public void setManager(Employee manager) {
         this.manager = manager;
	}
    
	public void setDepartment(Department depart) {
        department = depart;
	}
    public void setEmail(String email){
    	this.email = email;
    }
    
    public void setSkypeName(String skype){
    	this.skypeName = skype;
    }
    
    public void setJobTitle(JobTitle title) {
    	this.jobTitle = title;
    	
    }
	public void setAll(Employee e){
		setFirstName(e.firstName);
		setLastName(e.lastName);
		setMiddleInitial(e.middleInitial);
		setManager(e.manager);
		setDepartment(e.department);
		setEmail(e.email);
		setSkypeName(e.skypeName);
		setJobTitle(e.jobTitle);
		setIsActive(e.is_manager);
	}
    public String getFirstName() {

		return this.firstName;
	}
    
	public String getLastName() {
		return this.lastName;

	}

	public Character getMiddleInitial() {

		return this.middleInitial;
	}

	public Employee getManager() {
		return this.manager;
	}

	public Department getDepartment() {
		return this.department;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}
	public String getSkypeName() {
		// TODO Auto-generated method stub
		return this.skypeName;
	}
	public JobTitle getJobTitle() {
		// TODO Auto-generated method stub
		return this.jobTitle;
	}
	
	public String toString(){
		if(middleInitial == null)
			middleInitial = ' ';
		return firstName + " " + middleInitial + " " +lastName;
	} 

	
}
