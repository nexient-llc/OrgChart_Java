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

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 20)
	@Column(name="first_name",length=20)
	private String firstName;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	@Column(name="last_name",length=50)
	private String lastName;
	
	
	@Column(name="middle_initial", length=1, unique=false)
	private Character middleInitial;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "manager_id")
	private Employee manager;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 100)
	@Column(name = "email", length = 100, unique = true)
	private String email;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 100)
	@Column(name = "skype_name", length = 100, unique = true)
	private String skypeName;

	@ManyToOne(optional = false)
	@JoinColumn(name = "job_title_id", referencedColumnName="id")
	private JobTitle jobTitle;

	@ManyToOne(optional = false)
	@JoinColumn(name = "department_id")
	private Department department;
	
	@Column(name="is_manager")
	private Boolean isManager;
	

	public Employee getManager() {
		return manager;
	}

	

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;

	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;

	}

	public String getLastName() {
		return this.lastName;
	}

	public void setMiddleInitial(Character middleInitial) {
		this.middleInitial = middleInitial;

	}

	public Character getMiddleInitial() {
		return this.middleInitial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSkypeName() {
		return skypeName;
	}

	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}

	public JobTitle getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}
	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

}
