package com.systemsinmotion.orgchart.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class set_GetMiddleInitial {

	@Test
	public void test() 
	{
	
		Employee e= new Employee();
		e.setMiddleInitial("m");

		assertEquals("m", e.getMiddleInitial());
		
		
	}

}
