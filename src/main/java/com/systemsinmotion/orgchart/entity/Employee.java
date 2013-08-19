package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private int job_title_id;
	private int department_id;
	private int manager_id;
	
	@Id
	@Column (name = "id", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	
	@Column (name = "first_name", nullable = false, length = 20)
	public String getFirst_name() {
		return first_name;
	}
	
	@Column (name = "last_name", nullable = false, length = 50)
	public String getLast_name() {
		return last_name;
	}
	
	@Column (name = "email", unique = true, length = 100)
	public String getEmail() {
		return email;
	}
	
	@Column (name = "skype_name", unique = true, length = 100)
	public String getSkype_name() {
		return skype_name;
	}
	
	@Column (name = "is_manager")
	public boolean isIs_manager() {
		return is_manager;
	}
	
	@Column (name = "job_title_id")
	public int getJob_title_id() {
		return job_title_id;
	}
	
	@Column (name = "department_id")
	public int getDepartment_id() {
		return department_id;
	}
	
	@Column (name = "manager_id")
	public int getManager_id() {
		return manager_id;
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
	
	public void setJob_title_id(int job_title_id) {
		this.job_title_id = job_title_id;
	}
	
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
}
