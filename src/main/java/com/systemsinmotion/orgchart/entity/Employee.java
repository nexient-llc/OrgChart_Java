package com.systemsinmotion.orgchart.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements java.io.Serializable {

	private static final long serialVersionUID = -3263850083076272275L;

	private Integer employeeId;
	private JobTitle jobTitle;
	private Department department;
	private Employee manager;
	private String firstName;
	private String lastName;
	private String email;
	private String skypeName;
	private Boolean isManager;
	private Set<Department> departments = new HashSet<Department>(0);
	private Set<Employee> employees = new HashSet<Employee>(0);

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** minimal constructor */
	public Employee(Integer employeeId, String firstName, String lastName,
			Boolean isManager) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isManager = isManager;
	}

	/** full constructor */
	public Employee(Integer employeeId, JobTitle jobTitle,
			Department department, Employee employee, String firstName,
			String lastName, String email, String skypeName, Boolean isManager,
			Set<Department> departments, Set<Employee> employees) {
		this.employeeId = employeeId;
		this.jobTitle = jobTitle;
		this.department = department;
		this.manager = employee;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.skypeName = skypeName;
		this.isManager = isManager;
		this.departments = departments;
		this.employees = employees;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "EMPLOYEE_ID", unique = true, nullable = false)
	public Integer getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JOB_TITLE_ID")
	public JobTitle getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_ID")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID")
	public Employee getManager() {
		return this.manager;
	}

	public void setManager(Employee employee) {
		this.manager = employee;
	}

	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "SKYPE_NAME", length = 100)
	public String getSkypeName() {
		return this.skypeName;
	}

	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}

	@Column(name = "IS_MANAGER", nullable = false)
	public Boolean getIsManager() {
		return this.isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "manager")
	public Set<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "manager")
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}