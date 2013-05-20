package com.systemsinmotion.orgchart.entity;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements java.io.Serializable {

	private static final long serialVersionUID = -1676017339639047503L;
	
	private Integer 	id;
	private String 		firstName;
	private String 		lastName;
	private String 		email;
	private String 		skypeName;
	private Boolean 	isManager = false;
	private JobTitle 	jobTitle;
	private Department 	department;
	private Employee 	manager;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	public Integer getId() {
		return id;
	}
	
	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirstName() {
		return firstName;
	}
	
	@Column(name = "LAST_NAME", nullable = false, length = 50)
	public String getLastName() {
		return lastName;
	}

	@Column(name = "EMAIL", unique = true, length = 100)
	public String getEmail() {
		return email;
	}

	@Column(name = "SKYPE_NAME", unique = true, length = 100)
	public String getSkypeName() {
		return skypeName;
	}
	
	@Column(name = "IS_MANAGER")
	public Boolean getIsManager() {
		return isManager;
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
	public Employee getManager() {
		return manager;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}
	
	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}
	
	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

}
