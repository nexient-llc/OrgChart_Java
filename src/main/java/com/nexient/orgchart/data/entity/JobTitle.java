package com.nexient.orgchart.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitle extends BaseEntity {

	@Column(name = "DESCRIPTION", nullable = false, length = 45)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jobTitle")
	private Set<Employee> employees = new HashSet<Employee>(0);

	public JobTitle() {
	}

	private JobTitle(Builder builder) {
		this.description = builder.description;
		this.employees = builder.employees;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public static class Builder {
		private String description;
		private Set<Employee> employees = new HashSet<Employee>(0);

		Builder(String description) {
			this.description = description;
		}

		Builder employees(Set<Employee> employees) {
			this.employees = employees;
			return this;
		}

		JobTitle build() {
			return new JobTitle(this);
		}
	}
}