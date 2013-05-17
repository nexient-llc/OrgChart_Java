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