package com.systemsinmotion.orgchart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {

	private static final long serialVersionUID = 2786300800604338231L;
	
//	@NotNull
//	@NotEmpty
//	@Size(min = 1, max = 45)
	private Department department;
	@NotNull
	@NotEmpty
	private String firstName;
	@NotNull
	@NotEmpty
	private String lastName;
	private String email;
	private String skypeName;
	private Boolean isManager;
	private Employee manager;
	
	
	@Column(name = "is_manager")
	public Boolean getIsManager() {
		return this.isManager;
	}
	public void setIsManager(Boolean Manager) {
		this.isManager = Manager;
	}
	@Column(name = "email", length = 100 ) 
	public String getEmail() {
		return this.email;
	}
	@Column(name = "skype_name", length = 100)
	public String getSkypeName() {
		return this.skypeName;
	}
	
	@Column (name = "last_name", nullable = false, length = 50)
	public String getLastName() {
		return this.lastName;
	}
	@Column (name = "first_name", nullable = false, length = 20)
	public String getFirstName() {
		return this.firstName;
	}
	@ManyToOne
    @JoinColumn(name = "Department_ID", referencedColumnName = "ID")
	public Department getDepartment() {
		return this.department;
	}
	
	public void setDepartment(Department dept) {
		this.department = dept;
	}

	public void setFirstName(String fName) {
		this.firstName = fName;	
	}

	public void setLastName(String lName) {
		this.lastName = lName;
		
	}

	public void setSkypeName(String sName) {
		this.skypeName = sName;
		
	}

	public void setEmail(String emailNew) {
		this.email = emailNew;
		
	}
	@ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
	public Employee getManager() {
		return this.manager;
	}
	public void setManager(Employee Manager) {
		this.manager = Manager;
	}

	

}
