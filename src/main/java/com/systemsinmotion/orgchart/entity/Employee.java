package com.systemsinmotion.orgchart.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {

	private static final long serialVersionUID = 2786300800604338231L;
	
	private Department department;

	
	
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
}
