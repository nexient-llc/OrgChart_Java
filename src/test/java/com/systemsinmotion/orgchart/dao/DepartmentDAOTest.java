package com.systemsinmotion.orgchart.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.TestObject;
import com.systemsinmotion.orgchart.entity.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class DepartmentDAOTest {

	private static final String SOME_NEW_NAME = "Some New Name";

	private static final String NOT_PRESENT_VALUE = "XXX";

	private static final Integer NOT_PRESENT_ID = -666;

	private Department department;
	
	private Department parent;

	@Autowired
	DepartmentDAO departmentDAO;

	@Before
	public void before() throws Exception {
		parent = TestObject.department();
		parent.setDepartmentId(departmentDAO.save(parent));
		department = TestObject.department(parent);
		department.setDepartmentId(departmentDAO.save(department));
	}

	@After
	public void after() {
		departmentDAO.delete(department);
		departmentDAO.delete(parent);
	}
	
//	@Test
//	public void test() {
////		fail("Not yet implemented");
//		assertTrue("Not yet implemented", true);
//	}


	@Test
	@Rollback
	public void findAll_notNull() throws Exception {
		//System.out.println(departmentDAO.toString());
		List<Department> depts = departmentDAO.findAll();
		assertNotNull(depts);
		assertTrue(0 < depts.size());
	}

	@Test
	@Rollback
	public void findByDeptId_null() throws Exception {
		Department dept = departmentDAO.findById(NOT_PRESENT_ID);
		assertNull(dept);
	}

	@Test
	@Rollback
	public void findByDeptId() throws Exception {
		Department dept = departmentDAO.findById(this.department.getDepartmentId());
		System.out.println(dept.toString());
		assertNotNull(dept);
		assertEquals(this.department.getName(), dept.getName());
		assertNotNull(this.department.getParentDepartment());
		assertNotNull(this.department.getManager());
	}

	@Test
	@Rollback
	public void findByName_null() throws Exception {
		Department dept = departmentDAO.findByName(NOT_PRESENT_VALUE);
		assertNull(dept);
	}

	@Test
	@Rollback
	public void findByName() throws Exception {
		Department dept = departmentDAO.findByName(this.department.getName());
		assertNotNull(dept);
		assertEquals(this.department.getName(), dept.getName());
		assertNotNull(this.department.getParentDepartment());
		assertNotNull(this.department.getManager());
	}

	@Test
	@Rollback
	public void findByParentDeptId_null() throws Exception {
		List<Department> depts = departmentDAO.findByParentDepartment(new Department());
		assertEquals(0, depts.size());
	}

	@Test
	@Rollback
	public void findByParentDeptId() throws Exception {
		List<Department> depts = departmentDAO.findByParentDepartment(this.department.getParentDepartment());
		assertNotNull(depts);
		assertEquals(1, depts.size());
		Department dept = depts.get(0);
		assertEquals(this.department.getName(), dept.getName());
		assertNotNull(this.department.getParentDepartment());
		assertNotNull(this.department.getManager());
	}

	@Test
	@Rollback
	public void update() throws Exception {
		Department dept = departmentDAO.findByName(this.department.getName());
		dept.setName(SOME_NEW_NAME);
		departmentDAO.update(dept);

		dept = null;
		dept = departmentDAO.findByName(SOME_NEW_NAME);
		assertNotNull(dept);
		assertEquals(SOME_NEW_NAME, dept.getName());
	}

	@Test(expected = DataIntegrityViolationException.class)
	@Rollback
	public void duplicateName() throws Exception {
		Department dept = TestObject.department();
		dept.setName(this.department.getName());
		departmentDAO.save(dept);
	}
}
