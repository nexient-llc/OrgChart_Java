package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestControllerConfig;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;
import com.systemsinmotion.orgchart.web.View;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestControllerConfig.class)
public class DepartmentControllerTest {

	@Autowired
	DepartmentController controller;
	
	@Autowired
	private DepartmentService mockDepartmentService;
	
	@Autowired
	private Department mockDepartment;

	private List<Department> findAllDepartmentsList;
	
	Model model = new ExtendedModelMap();

	@Test
	public void testInit() {
		assertNotNull(controller);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldContainNewDepartmentList() {
		// Given
		String viewName = this.controller.doDepartments_GET(this.model);

		// When
		this.findAllDepartmentsList = (List<Department>) (this.model.asMap().get("depts"));
		// Then
		assertNotNull(this.findAllDepartmentsList);
		assertEquals(Entities.DEPT_ID, this.findAllDepartmentsList.get(0).getId());
		assertEquals(View.DEPARTMENTS, viewName);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void setDepartmentService() {
		this.controller.setDepartmentService(this.mockDepartmentService);

		// Given
		String viewName = this.controller.doDepartments_GET(this.model);

		// When
		this.findAllDepartmentsList = (List<Department>) (this.model.asMap().get("depts"));
		// Then
		assertNotNull(this.findAllDepartmentsList);
		assertEquals(Entities.DEPT_ID, this.findAllDepartmentsList.get(0).getId());
		assertEquals(View.DEPARTMENTS, viewName);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnDepartmentPagePost() {
		//Given
		controller.doDepartmentNew_POST(mockDepartment, model);
		//When
		findAllDepartmentsList = (List<Department>)model.asMap().get("depts");

		//Then
		assertNotNull(findAllDepartmentsList);
		assertTrue(findAllDepartmentsList.size() > 1);
		Department foundDepartment = findAllDepartmentsList.get(findAllDepartmentsList.size() - 1);
		assertEquals(Entities.DEPT_ID, foundDepartment.getId());
		verify(mockDepartmentService, atLeastOnce()).storeDepartment(mockDepartment);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnDepartmentPageUpdate() {
		mockDepartment.setParentDepartment(Entities.department(Entities.PARENT_DEPT_ID));
		
		//Given
		controller.doDepartmentUpdate_POST(mockDepartment, model);
		//When
		findAllDepartmentsList = (List<Department>)model.asMap().get("depts");

		//Then
		assertNotNull(findAllDepartmentsList);
		assertTrue(findAllDepartmentsList.size() > 1);
		Department foundDepartment = findAllDepartmentsList.get(findAllDepartmentsList.size() - 1);
		assertEquals(Entities.DEPT_ID, foundDepartment.getId());
		assertEquals(Entities.PARENT_DEPT_ID, foundDepartment.getParentDepartment().getId());
		verify(mockDepartmentService, atLeastOnce()).storeDepartment(mockDepartment);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doDepartmentDelete_DELETE() {
		controller.doDepartmentDelete_DELETE(Entities.DEPT_ID, model);
		findAllDepartmentsList = (List<Department>)model.asMap().get("depts");
		
		assertNotNull(findAllDepartmentsList);
		assertTrue(findAllDepartmentsList.isEmpty());

		// Reset
		controller.doDepartmentNew_POST(mockDepartment, model);
		findAllDepartmentsList = (List<Department>)model.asMap().get("depts");
		
		assertNotNull(findAllDepartmentsList);
		assertFalse(findAllDepartmentsList.isEmpty());		
	}

}
