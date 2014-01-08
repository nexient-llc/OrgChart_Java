package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {

	private static final long serialVersionUID = 2786300800604338231L;
	
	@NotNull
	@NotEmpty
	@Size(min = 1,max = 20)
	private String firstName;
	
	@NotNull
	@NotEmpty
	@Size(min = 1,max = 50)
	private String lastName;
	
	private Department department;

	private String email;
	
	private Employee manager;
	
	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirstName(){
		return this.firstName;
	}
	
	@Column(name = "LAST_NAME", nullable = false, length = 50)
	public String getLastName(){
		return this.lastName;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
	public Department getDepartment() {
		return department;
	}
	
	@Column(name = "EMAIL", unique = true)
	public String getEmail() {
		return this.email;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
	public Employee getManager(){
		return this.manager;
	}
	
	public void setEmail(String email){
		this.email = email;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	
}

