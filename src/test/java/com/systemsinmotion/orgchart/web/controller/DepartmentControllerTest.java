package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@Autowired
	private List<Department> mockDepartmentList;

	private List<Department> findAllDepartmentsList;

	private Model model = new ExtendedModelMap();

	private MockMvc mockMvc;

	private RedirectAttributes redirectAttributes;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		redirectAttributes = Mockito.mock(RedirectAttributes.class);
	}

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
		// Given
		controller.doDepartmentNew_POST(mockDepartment, model, redirectAttributes);
		// When
		findAllDepartmentsList = (List<Department>) model.asMap().get("depts");

		// Then
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

		// Given
		controller.doDepartmentUpdate_POST(mockDepartment, model);
		// When
		findAllDepartmentsList = (List<Department>) model.asMap().get("depts");

		// Then
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
		findAllDepartmentsList = (List<Department>) model.asMap().get("depts");

		assertNotNull(findAllDepartmentsList);
		assertTrue(findAllDepartmentsList.isEmpty());

		// Reset
		controller.doDepartmentNew_POST(mockDepartment, model, redirectAttributes);
		findAllDepartmentsList = (List<Department>) model.asMap().get("depts");

		assertNotNull(findAllDepartmentsList);
		assertFalse(findAllDepartmentsList.isEmpty());
	}

	@Test
	public void doDepartmentNew_POST_error() throws Exception {

		this.mockMvc.perform(post("/newDepart").param("name", Entities.DEPARTMENT_NAME).param("id", Entities.PARENT_DEPT_ID.toString())).andExpect(
				status().isFound());
		this.mockMvc.perform(post("/newDepart").param("name", Entities.DEPARTMENT_NAME).param("id", Entities.PARENT_DEPT_ID.toString())).andExpect(
				status().isConflict());
	}

	@Test
	public void doDepartmentFind_GET_noId() {
		when(mockDepartmentService.findDepartmentByName(Entities.DEPARTMENT_NAME)).thenReturn(mockDepartmentList);
		boolean nameAlreadyExists = controller.doDepartmentFind_GET(Entities.DEPARTMENT_NAME, -1);
		assertTrue(nameAlreadyExists);
	}

	@Test
	public void doDepartmentFind_GET_differentId() {
		when(mockDepartmentService.findDepartmentByName(Entities.DEPARTMENT_NAME)).thenReturn(mockDepartmentList);
		boolean nameAlreadyExists = controller.doDepartmentFind_GET(Entities.DEPARTMENT_NAME, Entities.NOT_PRESENT_ID);
		assertTrue(nameAlreadyExists);
	}

	@Test
	public void doDepartmentFind_GET_sameId() {
		when(mockDepartmentService.findDepartmentByName(Entities.DEPARTMENT_NAME)).thenReturn(mockDepartmentList);
		boolean nameAlreadyExists = controller.doDepartmentFind_GET(Entities.DEPARTMENT_NAME, Entities.DEPT_ID);
		assertFalse(nameAlreadyExists);
	}

	@Test
	public void doDepartmentFind_GET_differentName() {
		when(mockDepartmentService.findDepartmentByName(Entities.NOT_PRESENT_VALUE)).thenReturn(mockDepartmentList);
		boolean nameAlreadyExists = controller.doDepartmentFind_GET(Entities.NOT_PRESENT_VALUE, Entities.DEPT_ID);
		assertFalse(nameAlreadyExists);
	}

	@Test
	public void doDepartments_ajax_GET() {
		Page<Department> depts = controller.doDepartments_ajax_GET(0, model);
		assertNotNull(depts);
		assertEquals(Entities.DEPT_ID, depts.getContent().get(0).getId());
	}
}
