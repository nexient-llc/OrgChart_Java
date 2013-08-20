package com.systemsinmotion.orgchart.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "EMPLOYEE")
public class Employee {

	private Integer id;
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
	public Integer getId() {
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
	
	@Column (name = "EMAIL", unique = true, nullable = false, length = 100)
	public String getEmail() {
		return email;
	}
	
	@Column (name = "SKYPE_NAME", unique = true, nullable = false, length = 100)
	public String getSkype_name() {
		return skype_name;
	}
	
	@Column (name = "IS_MANAGER")
	public boolean isIs_manager() {
		return is_manager;
	}
	
	@ManyToMany
	@JoinColumn (name = "JOB_TITLE_ID", referencedColumnName = "ID", nullable = false)
	public JobTitle getJobTitle() {
		return jobTitle;
	}

	@ManyToOne
	@JoinColumn (name="DEPARTMENT_ID", referencedColumnName = "ID", nullable = false)
	public Department getDepartment() {
		return department;
	}

	@ManyToOne
	@JoinColumn (name = "MANAGER_ID", referencedColumnName = "ID")
	public Employee getManager() {
		return manager;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
	
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSkypeName(String skype_name) {
		this.skype_name = skype_name;
	}
	
	public void setIsManager(boolean is_manager) {
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
