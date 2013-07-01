package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class set_GetLastName {

	@Test
	public void test()
	{
		Employee e= new Employee();
		e.setLastName("jones");
		
		assertEquals("jones", e.getLastName());
	
	}

}
