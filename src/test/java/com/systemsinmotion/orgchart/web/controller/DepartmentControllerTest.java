package com.systemsinmotion.orgchart.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.TestControllerConfig;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestControllerConfig.class)
// @RunWith(MockitoJUnitRunner.class)
public class DepartmentControllerTest {

	@Autowired
	private DepartmentController controller;
	@Autowired
	private Department mockDepartment;

	@Mock
	private Model model = new ExtendedModelMap();

	private List<Department> findAllDepartmentsList;

	@Before
	public void setup() {

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldContainNewDepartmentList() {
		// Given
		@SuppressWarnings("unused")
		String viewName = this.controller.doDepartments_GET(this.model);

		// When
		this.findAllDepartmentsList = (List<Department>) (this.model.asMap()
				.get("depts"));
		// Then
		assertNotNull(this.findAllDepartmentsList);
		assertEquals(Entities.DEPT_ID, this.findAllDepartmentsList.get(0)
				.getId());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldUpdateOnDepartmentPagePost() {

		model.addAttribute("depts", findAllDepartmentsList);
		// Given
		controller.doDepartment_POST(mockDepartment, model);
		// When
		findAllDepartmentsList = (List<Department>) model.asMap().get("depts");

		// Then
		assertNotNull(findAllDepartmentsList);
		assertTrue(findAllDepartmentsList.size() > 1);
		assertEquals(Entities.DEPT_ID, findAllDepartmentsList.get(1).getId());
		assertEquals(findAllDepartmentsList.get(1).getName(),
				mockDepartment.getName());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testModelShouldEditOnDepartmentPagePost() {

		model.addAttribute("depts", findAllDepartmentsList);
		// Given
		controller.doEditDepartment_EDIT(mockDepartment, model);
		// When
		findAllDepartmentsList = (List<Department>) model.asMap().get("depts");

		// Then
		assertNotNull(findAllDepartmentsList);
		assertTrue(findAllDepartmentsList.size() > 1);
		assertEquals(Entities.DEPT_ID, findAllDepartmentsList.get(1).getId());
		assertEquals(findAllDepartmentsList.get(1).getName(),
				mockDepartment.getName());

	}

	@Test
	public void testModelShouldHaveJsonString() {

		String json = controller.doDepartment_GET(Entities.DEPT_ID);

		assertNotNull(json);
		assertTrue(json.length() > 1);

	}

	@Test
	public void testShoudDeleteDepartment() {

		Department mockedDepartment = mock(Department.class);

		String dept = controller.doDepartments_DELETE(mockedDepartment,
				this.model);

		assertTrue(mockedDepartment.isActive() == false);
		assertNotNull(dept);

	}

	@Test
	public void testSetDepartmentRepo() {

		DepartmentService mockDepartmentService = mock(DepartmentService.class);
		DepartmentController localController = new DepartmentController();
		// // doCallRealMethod().when(mockController).setDepartmentService(
		// // controller.departmentService);
		// // mockController.setDepartmentService(controller.departmentService);
		// //
		// // verify(mockController).setDepartmentService(
		// // controller.departmentService);
		//
		// localController.setDepartmentService(mockDepartmentService);
		// assertNotNull(controller.getDepartmentService());
		// assertSame(mockDepartmentService,
		// localController.getDepartmentService());

	}

}
