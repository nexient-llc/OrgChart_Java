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

	/* VARIABLES */
	
	private static final long serialVersionUID = -6844033771097189452L;
	
	//base
	private Integer jobTitleID;
	private String desc;
	
	//collections
	private Set<Employee> employees = new HashSet<Employee>(0);
	
	
	/* CONSTRUCTORS */
	
	//default
	public JobTitle()
	{
		
	}
	
	//basic
	public JobTitle(String description) 
	{
		this.desc = description;
	}

	//complete
	public JobTitle(String description, Set<Employee> employees) 
	{
		this.desc = description;
		this.employees = employees;
	}
	
	
	/* ACCESSORS */
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="JOB_TITLE_ID", unique=true, nullable=false)
		public Integer getJobTitleID() {
			return this.jobTitleID;
		}
	
		public void setJobTitleID(Integer jobTitleID) {
			this.jobTitleID = jobTitleID;
		}

	@Column(name="DESCRIPTION", nullable=false, length=45)
		public String getDesc() {
			return this.desc;
		}
	
		public void setDesc(String description) {
			this.desc = description;
		}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="jobTitle")
		public Set<Employee> getEmployees() {
			return this.employees;
		}
	
		public void setEmployees(Set<Employee> employees) {
			this.employees = employees;
		}
	
}
