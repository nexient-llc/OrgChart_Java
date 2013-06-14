package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "EMPLOYEE")
public class Employee { 
	
	@NotNull
	@NotEmpty
	private Department department;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 100)
	private String email;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 20)
	private String first_name;

	private Integer id;

	@NotNull
	@NotEmpty
	private Boolean is_manager;

	@NotNull
	@NotEmpty
	private JobTitle job_title;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	private String last_name;

	@NotNull
	@NotEmpty
	private Employee manager;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 100)
	private String skype_name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT", referencedColumnName = "ID")
	public Department getDepartment() {
		return department;
	}

	@Column(name = "EMAIL", nullable = false, length = 100)
	public String getEmail() {
		return email;
	}

	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirst_Name() {
		return first_name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	@Column(name = "IS_MANAGER", nullable = false)
	public Boolean getIs_Manager() {
		return is_manager;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JOB_TITLE", referencedColumnName = "ID")
	public JobTitle getJob_Title() {
		return job_title;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 50)
	public String getLast_Name() {
		return last_name;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER", referencedColumnName = "ID")
	public Employee getManager() {
		return manager;
	}

	@Column(name = "SKYPE_NAME", nullable = false, length = 100)
	public String getSkype_name() {
		return skype_name;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirst_Name(String first_name) {
		this.first_name = first_name;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setIs_Manager(Boolean is_manager) {
		this.is_manager = is_manager;
	}

	public void setJob_Title(JobTitle jobtitle) {
		this.job_title = jobtitle;
	}

	public void setLast_Name(String last_name) {
		this.last_name = last_name;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public void setskype_name(String skype_name) {
		this.skype_name = skype_name;
	}
	
}
