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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="JOB_TITLE")
public class JobTitle implements java.io.Serializable {
	
	//VARIABLES
	
	private static final long serialVersionUID = 1L;
	
	//BASE VARIBLES
	private Integer jobTitleID;
	private String desc;
	
	//COLLECTIONS
	private Set<Employee> employees= new HashSet<Employee>();
	
	//DEFAULT CONSTRUCTOR
	public JobTitle()
	{
		
	}
	
	// MINIMAL CONSTRUCTOR
	public JobTitle(String description)
	{
		
		this.desc = description;
		
	}
	
	//FULL CONSTRUCTOR
	public JobTitle(String description, Set<Employee> employees)
	{
		
		this.desc=description;
		this.employees=employees;
		
	}

	//ACCESSORS
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="JOB_TITLE_ID", unique=true, nullable=false)
		public Integer getJobTitleID() {
			return jobTitleID;
		}
	
		public void setJobTitleID(Integer jobTitleID) {
			this.jobTitleID = jobTitleID;
		}

	@Column(name="DESCRIPTION", nullable=false, length=45)
		public String getDesc() {
			return desc;
		}
	
		public void setDesc(String desc) {
			this.desc = desc;
		}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="jobTitle")
		public Set<Employee> getEmployees() {
			return employees;
		}
	
		public void setEmployees(Set<Employee> employees) {
			this.employees = employees;
		}
	
}
