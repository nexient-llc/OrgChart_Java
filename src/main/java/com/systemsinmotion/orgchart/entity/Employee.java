package com.systemsinmotion.orgchart.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;


@Entity
@Table (name="EMPLOYEE")
public class Employee implements Serializable 
{
	private static final long serialVersionUID = 2786300800604338231L;
	
	private int Id;
	
	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	@Column(name = "ID", nullable = false, unique = true)
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	@NotNull
	private String email;
	
	@NotNull
	@Size(min = 1, max = 20)
	private String firstName;
	
	@NotNull
	@Size(min = 1, max = 45)
	private String lastName;
		
	@NotNull
	@Size(min = 1, max = 45)
	private String skypeName;
	
	@NotNull
	private Boolean isManager;

//	private int manager_Id;
//	
//	@Column(name="MANAGER_ID")
//	public int getManager_Id() {
//		return manager_Id;
//	}
//	public void setManager_Id(int manager_Id) {
//		this.manager_Id = manager_Id;
//	}
	
	private Department department;
		
	@NotNull
	private JobTitle jobTitle;
	
	@Column(name = "IS_MANAGER")
	public Boolean getIsManager() {
		return isManager;
	}
	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}
	
	@Column(name="FIRST_NAME", nullable = false, length = 20)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "SKYPE_NAME")
	public String getSkypeName() {
		return skypeName;
	}
	public void setSkypeName(String skype) {
		this.skypeName = skype;
	}
	
	@NotNull
	@ManyToOne (cascade=CascadeType.ALL)
	@JoinColumn(name = "DEPARTMENT_ID")
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@ManyToOne (cascade=CascadeType.ALL)
	@JoinColumn(name = "JOB_TITLE_ID")
	public JobTitle getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}
	
}
