package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "DEPARTMENT")
public class Department extends BaseEntity {

	private static final long serialVersionUID = -5379179412533671591L;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String name;

	private Department parentDepartment;

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME", nullable = false, unique = true, length = 50)
	public String getName() {
		return this.name;
	}

	@ManyToOne
	@JoinColumn(name = "PARENT_DEPARTMENT_ID", referencedColumnName = "ID")
	public Department getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(Department parentDepartment) {
		this.parentDepartment = parentDepartment;
	}
}