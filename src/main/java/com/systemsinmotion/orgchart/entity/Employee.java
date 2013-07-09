package com.systemsinmotion.orgchart.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table (name="EMPLOYEE")
public class Employee implements Serializable 
{
	private static final long serialVersionUID = 2786300800604338231L;
	
	private Integer id;	
	
	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	@Column(name = "ID", unique = true , nullable = false )
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
	@NotNull
	@Size(min = 1, max = 20)
	private String firstName;
	
	
	@Column(name="FIRST_NAME", nullable = false, length = 20)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	
	@NotNull
	@Size(min = 1, max = 45)
	private String lastName;
	
	
	
	
	@NotNull
	private String email;
		
	
	
	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

		
	@NotNull
	@Size(min = 1, max = 45)
	private String skypeName;
	
	
	@Column(name = "SKYPE_NAME")
	public String getSkypeName() {
		return skypeName;
	}
	public void setSkypeName(String skype) {
		this.skypeName = skype;
	}
	
	
	
	@NotNull
	private Boolean is_Manager;

	@Column(name = "IS_MANAGER")
	public Boolean getIsManager() {
		return is_Manager;
	}
	public void setIsManager(Boolean isManager) {
		this.is_Manager = isManager;
	}
	
		
	private Employee manager;
		
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID" , referencedColumnName = "ID")
	public Employee getManager() 
	{
		return this.manager;
	}
	
	public void setManager(Employee manager) {
		this.manager = manager;
		
	}
	
	private Department department;
		
	@NotNull
	@ManyToOne (cascade=CascadeType.ALL)
	@JoinColumn(name = "DEPARTMENT_ID")	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
		
	
	private JobTitle jobTitle;
	
	@ManyToOne (cascade=CascadeType.ALL)
	@JoinColumn(name = "JOB_TITLE_ID")
	public JobTitle getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}
	
		
	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

}
