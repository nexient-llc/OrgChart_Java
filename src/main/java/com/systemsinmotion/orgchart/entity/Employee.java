package com.systemsinmotion.orgchart.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity 
{

	private static final long serialVersionUID = 2786300800604338231L;
	
	private Department department;
	
	private String First_Name;
	
	private String Last_Name;
	
	private String Skype_Name;
	
	private String Email;
	
	private boolean IsManager;

	public Department getDepartment()
	{
		return department;
	}

	public void setDepartment(Department department) 
	{
		this.department = department;
	}

	public void setFirstName(String firstName) 
	{
		this.First_Name = firstName;		
	}

	public void setLastName(String lastName)
	{
		this.Last_Name = lastName;
	}

	public void setEmail(String string)
	{
		this.Email = string;
	}

	public void setSkypeName(String string) 
	{
		this.Skype_Name = string;	
	}

	public void setIsManager(boolean b) 
	{
		this.IsManager = b;
	}
	
	
	
	

}
