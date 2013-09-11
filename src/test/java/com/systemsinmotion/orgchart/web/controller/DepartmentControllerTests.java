package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.web.View;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class DepartmentControllerTests {
	
	@Autowired
	DepartmentController controller;
	
	DepartmentService mockDepartmentService = mock(DepartmentService.class);
	Department mockDepartment = mock(Department.class);
	Department mockDepartment2 = mock(Department.class);
	Model model;
	ArrayList<Department> departments;
	
	@Before
	public void before() {
		model = new ExtendedModelMap();
		departments = new ArrayList<Department>(0);
		
		when(mockDepartment.getId()).thenReturn(Entities.DEPT_ID);
		departments.add(mockDepartment);
		
		when(mockDepartmentService.findAllActiveDepartments()).thenReturn(departments);
		
		controller.setDepartmentService(mockDepartmentService);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void doDepartments_GET() {
		String returnvalue = controller.doDepartments_GET(model);
		ArrayList<Department> depts = (ArrayList<Department>) model.asMap().get("depts");
		assertNotNull(returnvalue);
		assertNotNull(depts);
		assertEquals(View.DEPARTMENTS, returnvalue);
		assertEquals(Entities.DEPT_ID, depts.get(0).getId());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void doDepartments_POST() {
		String returnvalue = controller.doDepartments_POST(mockDepartment2, model);
		ArrayList<Department> depts = (ArrayList<Department>) model.asMap().get("depts");
		Mockito.verify(mockDepartmentService).storeDepartment(mockDepartment2);
		assertNotNull(returnvalue);
		assertNotNull(depts);
		assertEquals(View.DEPARTMENTS, returnvalue);
		assertEquals(Entities.DEPT_ID, depts.get(0).getId());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void doDepartments_PUT() {
		String returnvalue = controller.doDepartments_PUT(mockDepartment2, model);
		ArrayList<Department> depts = (ArrayList<Department>) model.asMap().get("depts");
		Mockito.verify(mockDepartmentService).updateDepartment(mockDepartment2);
		assertNotNull(returnvalue);
		assertNotNull(depts);
		assertEquals(View.DEPARTMENTS, returnvalue);
		assertEquals(Entities.DEPT_ID, depts.get(0).getId());
	}
}
