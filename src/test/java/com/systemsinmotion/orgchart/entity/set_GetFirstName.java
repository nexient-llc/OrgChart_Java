package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class set_GetFirstName {

	@Test
	public void test() 
	{
		
	Employee e = new Employee();	
	e.setFirstName("sam");
	
	assertEquals("sam", e.getFirstName());	
	}

}
