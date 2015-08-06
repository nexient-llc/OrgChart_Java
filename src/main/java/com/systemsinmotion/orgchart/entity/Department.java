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
@Table(name = "DEPARTMENT")
public class Department implements java.io.Serializable {

	private static final long serialVersionUID = -5379179412533671591L;

	private Integer departmentId;
	private Department parentDepartment;
	private Employee manager;
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max=45)
	private String name;
	private Set<Department> departments = new HashSet<Department>(0);
	private Set<Employee> employees = new HashSet<Employee>(0);

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** minimal constructor */
	public Department(String name) {
		this.name = name;
	}

	/** full constructor */
	public Department(Department parentDepartment, Employee deptManager, String deptName,
			Set<Department> subDepartments, Set<Employee> deptEmployees) {
		this.parentDepartment = parentDepartment;
		this.manager = deptManager;
		this.name = deptName;
		this.departments = subDepartments;
		this.employees = deptEmployees;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DEPARTMENT_ID", unique = true, nullable = false)
	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_DEPARTMENT_ID")
	public Department getParentDepartment() {
		return this.parentDepartment;
	}

	public void setParentDepartment(Department department) {
		this.parentDepartment = department;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID")
	public Employee getManager() {
		return this.manager;
	}

	public void setManager(Employee employee) {
		this.manager = employee;
	}

	@Column(name = "NAME", unique = true, nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentDepartment")
	public Set<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}