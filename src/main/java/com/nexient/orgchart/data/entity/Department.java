package com.nexient.orgchart.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENT")
public class Department extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_DEPARTMENT_ID")
	private Department parentDepartment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID")
	private Employee manager;

	@Column(name = "NAME", unique = true, nullable = false, length = 45)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parentDepartment")
	private Set<Department> departments = new HashSet<Department>(0);

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private Set<Employee> employees = new HashSet<Employee>(0);

	public Department() {
	}

	private Department(Builder b) {
		this.parentDepartment = b.parentDepartment;
		this.manager = b.manager;
		this.name = b.name;
		this.departments = b.departments;
		this.employees = b.employees;
	}

	public Department getParentDepartment() {
		return parentDepartment;
	}

	public Employee getManager() {
		return manager;
	}

	public String getName() {
		return name;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public static class Builder {
		private final String name;
		private Department parentDepartment;
		private Employee manager;
		private Set<Department> departments = Collections.EMPTY_SET;
		private Set<Employee> employees = Collections.EMPTY_SET;

		public Builder(String name) {
			this.name = name;
		}

		public Builder parentDepartment(Department dept) {
			this.parentDepartment = dept;
			return this;
		}

		public Builder manager(Employee manager) {
			this.manager = manager;
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

		public Department build() {
			return new Department(this);
		}
	}
}