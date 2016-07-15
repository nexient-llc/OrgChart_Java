package com.nexient.orgchart.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "JOB_TITLE_ID")
	private JobTitle jobTitle;

	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID")
	private Employee manager;

	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false, length = 45)
	private String lastName;

	@Column(name = "EMAIL", length = 100)
	private String email;

	@Column(name = "IS_MANAGER", nullable = false)
	private Boolean isManager = false;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
	private Set<Department> departments = new HashSet<Department>(0);

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
	private Set<Employee> employees = new HashSet<Employee>(0);

	public Employee() {
	}

	private Employee(Builder builder) {
		this.jobTitle = builder.jobTitle;
		this.department = builder.department;
		this.manager = builder.manager;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		this.isManager = builder.isManager;
		this.departments = builder.departments;
		this.employees = builder.employees;
	}

	public JobTitle getJobTitle() {
		return jobTitle;
	}

	public Department getDepartment() {
		return department;
	}

	public Employee getManager() {
		return manager;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public static class Builder {
		private final String firstName;
		private final String lastName;
		private JobTitle jobTitle;
		private Department department;
		private Employee manager;
		private String email;
		private Boolean isManager;
		private Set<Department> departments;
		private Set<Employee> employees;

		Builder(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public Builder jobTitle(JobTitle jobTitle) {
			this.jobTitle = jobTitle;
			return this;
		}

		public Builder department(Department department) {
			this.department = department;
			return this;
		}

		public Builder manager(Employee manager) {
			this.manager = manager;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder isManager(Boolean isManager) {
			this.isManager = isManager;
			return this;
		}

		public Builder departments(Set<Department> departments) {
			this.departments = departments;
			return this;
		}

		public Builder employees(Set<Employee> employees) {
			this.employees = employees;
			return this;
		}

		public Employee build()

		{
			return new Employee(this);
		}
	}
}