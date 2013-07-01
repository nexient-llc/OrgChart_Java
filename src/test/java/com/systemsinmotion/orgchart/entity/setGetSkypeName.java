package com.systemsinmotion.orgchart.entity;
import static org.junit.Assert.*;

import org.junit.Test;

import com.systemsinmotion.orgchart.entity.Employee;


public class setGetSkypeName {

	@Test
	public void test() {
		
		Employee employee =new Employee();
		employee.setSkypeName("aaa");
		
		assertEquals("aaa", employee.getSkypeName());
	}

}
