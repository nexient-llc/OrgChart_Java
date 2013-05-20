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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements java.io.Serializable {

	@NotNull
	private Integer employeeId;
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 20)
	private String firstName;
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String lastName;
	@Size(min = 0, max = 100)
	private String email;
	@Size(min = 0, max = 100)
	private String skypeName;
	private Integer jobTitleId;
	@NotNull
	private Boolean isManager;
	private Integer managerId;
	private Integer departmentId;
	// Constructors

	/** default constructor */
	public Employee() {
		
	}

	/** minimal constructor */
//	public Employee(String name) {
//		this.name = name;
//	}

	/** Partial Contructor **/
//	public Department(Department parentDepartment,  String deptName,
//		Set<Department> subDepartments) {
//		this.parentDepartment = parentDepartment;
//		this.name = deptName;
//		this.departments = subDepartments;
//	}
	
	/** full constructor */
//	public Department(Department parentDepartment, Employee deptManager, String deptName,
//			Set<Department> subDepartments, Set<Employee> deptEmployees) {
//		this.parentDepartment = parentDepartment;
//		this.manager = deptManager;
//		this.name = deptName;
//		this.departments = subDepartments;
//		this.employees = deptEmployees;
//	}

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
	
	@Column(name = "JOB_TITLE_ID")
	public Integer getJobTitleId() {
		return this.jobTitleId;
	}
	
	public void setJobTitleId(Integer jobTitleId) {
		this.jobTitleId = jobTitleId;
	}
	
//	@Column(name = "IS_MANAGER", nullable = false)
//	public Boolean getIsManager() {
//		return this.isManager;
//	}
//	
//	public void setIsManager(Boolean isManager) {
//		this.isManager = isManager;
//	}
	
//	@Column(name = "DEPARTMENT_ID")
//	public Integer getDepartmenrId() {
//		return this.departmentId;
//	}
//	
//	public void setDepartmentId(Integer departmentId) {
//		this.departmentId = departmentId;
//	}
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "PARENT_DEPARTMENT_ID")
//	public Department getParentDepartment() {
//		return this.parentDepartment;
//	}
//
//	public void setParentDepartment(Department department) {
//		this.parentDepartment = department;
//	}

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "MANAGER_ID")
//	public Employee getManager() {
//		return this.manager;
//	}

//	public void setManager(Employee employee) {
//		this.manager = employee;
//	}

//	@Column(name = "NAME", nullable = false, length = 45)
//	public String getName() {
//		return this.name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentDepartment")
//	public Set<Department> getDepartments() {
//		return this.departments;
//	}
//
//	public void setDepartments(Set<Department> departments) {
//		this.departments = departments;
//	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
//	public Set<Employee> getEmployees() {
//		return this.employees;
//	}
//
//	public void setEmployees(Set<Employee> employees) {
//		this.employees = employees;
//	}

}