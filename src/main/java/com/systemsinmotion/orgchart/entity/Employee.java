package com.systemsinmotion.orgchart.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	private Collection<JobTitle> jobTitle = new ArrayList<JobTitle>();
	private Collection<Department> department = new ArrayList<Department>();
	private Collection<Employee> manager = new ArrayList<Employee>();

	@Id
	@Column (name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	
	@Column (name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirst_name() {
		return first_name;
	}
	
	@Column (name = "LAST_NAME", nullable = false, length = 50)
	public String getLast_name() {
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
	
	@ManyToOne
	@JoinColumn (name="JOB_TITLE_ID", referencedColumnName="ID")
	public Collection<JobTitle> getJobTitle() {
		return jobTitle;
	}

	@ManyToOne
	@JoinColumn (name="DEPARTMENT_ID", referencedColumnName="ID")
	public Collection<Department> getDepartment() {
		return department;
	}

	@ManyToOne
	@JoinColumn (name="MANAGER_ID", referencedColumnName="ID")
	public Collection<Employee> getManager() {
		return manager;
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
	
	public void setManager(Collection<Employee> manager) {
		this.manager = manager;
	}
	
	public void setDepartment(Collection<Department> department) {
		this.department = department;
	}

	public void setJobTitle(Collection<JobTitle> jobTitle) {
		this.jobTitle = jobTitle;
	}
}
