package com.systemsinmotion.orgchart.entity;
import static org.junit.Assert.*;

import org.junit.Test;

import com.systemsinmotion.orgchart.entity.Employee;


public class set_getEmail {

	@Test
	public void test() {
		
		Employee employee =new Employee();
		employee.setEmail("blank.com");
		
		assertEquals("blank.com", employee.getEmail());
	}

}
