package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "DEPARTMENT")
public class Department implements java.io.Serializable {

	private static final long serialVersionUID = -5379179412533671591L;

	private Integer departmentId;
	private Integer managerId;

	private Department parentDepartment;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getDepartmentId() {
		return this.departmentId;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_DEPARTMENT_ID", referencedColumnName = "ID")
	public Department getParentDepartment() {
		return this.parentDepartment;
	}

	public void setParentDepartment(Department department) {
		this.parentDepartment = department;
	}

	@Column(name = "MANAGER_ID")
	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

}