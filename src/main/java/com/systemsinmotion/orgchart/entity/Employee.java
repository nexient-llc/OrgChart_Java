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
	
	private Integer id;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 20)
	private String first_name;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	private String last_name;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 100)
	private String email;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 100)
	private String skype_name;

	@NotNull
	@NotEmpty
	private Boolean is_manager;

	@NotNull
	@NotEmpty
	private Department department;
	
	@NotNull
	@NotEmpty
	private Job_Title job_title;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirst_name() {
		return first_name;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 50)
	public String getLast_name() {
		return last_name;
	}

	@Column(name = "EMAIL", nullable = false, length = 100)
	public String getEmail() {
		return email;
	}

	@Column(name = "SKYPE_NAME", nullable = false, length = 100)
	public String getSkype_name() {
		return skype_name;
	}

	@Column(name = "IS_MANAGER", nullable = false)
	public Boolean getIs_manager() {
		return is_manager;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT", referencedColumnName = "ID")
	public Department getDepartment() {
		return department;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JOB_TITLE", referencedColumnName = "ID")
	public Job_Title getJob_title() {
		return job_title;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSkype_name(String skype_name) {
		this.skype_name = skype_name;
	}

	public void setIs_manager(Boolean is_manager) {
		this.is_manager = is_manager;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setJob_title(Job_Title job_title) {
		this.job_title = job_title;
	}

}
