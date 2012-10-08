package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class LookupServiceTests {

	@Autowired
//	LookupService lookupService;

	@Test
	public void findDepartments() {
//		List<Department> depts = lookupService.findDepartments();
//		assertNotNull(depts);
		// assertTrue(0 < depts.size());
	}

	@Test
	public void findEmployees() {
//		List<Employee> emps = lookupService.findEmployees();
//		assertNotNull(emps);
	}
}
