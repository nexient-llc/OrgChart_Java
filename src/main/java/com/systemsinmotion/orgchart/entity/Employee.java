package com.systemsinmotion.orgchart.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "EMPLOYEE")
public class Employee {

	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String skype_name;
	private boolean is_manager;
	
	private JobTitle jobTitle = new JobTitle();
	private Department department = new Department();
	private Employee manager = new Employee();

	@Id
	@Column (name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	
	@Column (name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirstName() {
		return first_name;
	}
	
	@Column (name = "LAST_NAME", nullable = false, length = 50)
	public String getLastName() {
		return last_name;
	}
	
	@Column (name = "EMAIL", unique = true, length = 100)
	public String getEmail() {
		return email;
	}
	
	@Column (name = "SKYPE_NAME", unique = true, length = 100)
	public String getSkype_name() {
		return skype_name;
	}
	
	@Column (name = "IS_MANAGER")
	public boolean isIs_manager() {
		return is_manager;
	}
	
	@Column (name="JOB_TITLE_ID")
	public int getJobTitle() {
		return jobTitle.getId();
	}

	@Column (name="DEPARTMENT_ID")
	public int getDepartment() {
		return department.getId();
	}

	@Column (name = "MANAGER_ID")
	public int getManager() {
		return manager.getId();
	}
	
	public void setId(int id) {
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
	
	public void setIs_manager(boolean is_manager) {
		this.is_manager = is_manager;
	}
	
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}
}
