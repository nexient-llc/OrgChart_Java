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

	private static final long serialVersionUID = 2786300800604338231L;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String firstName;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String lastName;
	private String email;
	private String skypeName;
	private Boolean isManager;
	private JobTitle jobTitleId;
	private Employee manager;
	private Department department;

	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 50)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "EMAIL", nullable = false, length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "SKYPE_NAME", nullable = false, length = 50)
	public String getSkypeName() {
		return skypeName;
	}

	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}

	@Column(name = "IS_MANAGER", nullable = false, length = 50)
	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	@ManyToOne
	@JoinColumn(name = "JOB_TITLE_ID", referencedColumnName = "ID")
	public JobTitle getJobTitleId() {
		return jobTitleId;
	}

	public void setJobTitleId(JobTitle jobTitleId) {
		this.jobTitleId = jobTitleId;
	}

	@ManyToOne
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

}
