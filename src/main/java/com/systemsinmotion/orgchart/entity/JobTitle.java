package com.systemsinmotion.orgchart.entity;

public class JobTitle {
	
	private int ID;
	private String Name;

	public void setId(Integer id) 
	{
		this.ID = id;
	}

	public int getId() 
	{
		return this.ID;
	}

	public String getName() 
	{
		return this.Name;
	}

	public void setName(String name) 
	{
		this.Name = name;		
	}

}
