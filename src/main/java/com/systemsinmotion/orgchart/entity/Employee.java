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

@Entity
@Table(name="EMPLOYEE")
public class Employee implements java.io.Serializable {

	/* VARIABLES */
	
	private static final long serialVersionUID = 1L;

	//base
	private Integer empID;
	private String firstName;
	private String lastName;
	private String email;
	private String skypeName;
	private Boolean isManager;
	
	//object
	private JobTitle jobTitle;
	private Department department;
	private Employee manager;
	
	//collection
	private Set<Department> departments = new HashSet<Department>(0);
	private Set<Employee> employees = new HashSet<Employee>(0);
	
	
	/* CONSTRUCTORS */
	
	//default
	public Employee()
	{
		
	}
	
	//basic
	public Employee(Integer empID, String firstName, String lastName, Boolean isManager) 
	{
		this.empID = empID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isManager = isManager;
	}
		
	//complete
	public Employee(Integer empID, String firstName, String lastName, String email, String skypeName, 
				Boolean isManager, JobTitle jobTitle, Department dept, Employee manager, 
				Set<Department> departments, Set<Employee> employees) 
	{
		this.empID = empID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.skypeName = skypeName;
		this.isManager = isManager;
		this.jobTitle = jobTitle;
		this.department = dept;
		this.manager = manager;
		this.departments = departments;
		this.employees = employees;
	}

	/* ACCESSORS */
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name = "EMPLOYEE_ID", unique = true, nullable = false)
		public Integer getEmpID() {
			return empID;
		}
	
		public void setEmpID(Integer empID) {
			this.empID = empID;
		}
		
	@Column(name="FIRST_NAME", nullable=false, length=20)
		public String getFirstName() {
			return firstName;
		}
	
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

	@Column(name="LAST_NAME", nullable=false, length=45)
		public String getLastName() {
			return lastName;
		}
	
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

	@Column(name="EMAIL", length=100)
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}

	@Column(name="SKYPE_NAME", length=100)
		public String getSkypeName() {
			return skypeName;
		}
	
		public void setSkypeName(String skypeName) {
			this.skypeName = skypeName;
		}

	@Column(name="IS_MANAGER", nullable=false)
		public Boolean getIsManager() {
			return isManager;
		}
	
		public void setIsManager(Boolean isManager) {
			this.isManager = isManager;
		}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="JOB_TITLE_ID")
		public JobTitle getJobTitle() {
			return jobTitle;
		}
	
		public void setJobTitle(JobTitle jobTitle) {
			this.jobTitle = jobTitle;
		}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DEPARTMENT_ID")
		public Department getDept() {
			return department;
		}
	
		public void setDept(Department dept) {
			this.department = dept;
		}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MANAGER_ID")
		public Employee getManager() {
			return manager;
		}
	
		public void setManager(Employee manager) {
			this.manager = manager;
		}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="manager")
		public Set<Department> getDepartments() {
			return departments;
		}
	
		public void setDepartments(Set<Department> departments) {
			this.departments = departments;
		}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="manager")
		public Set<Employee> getEmployees() {
			return employees;
		}
	
		public void setEmployees(Set<Employee> employees) {
			this.employees = employees;
		}
	
	
	
}
