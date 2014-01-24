package com.systemsinmotion.orgchart.data;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.JPAConfig;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@WebAppConfiguration("/src/main/webapp")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeRepositoryTest {

	private static Random random = new Random();

	private static final String SOME_NEW_NAME = "Some New Name";

	private static final String NOT_PRESENT_VALUE = "XXX";

	private static final Integer NOT_PRESENT_ID = -666;

	private Employee employee;
    private Department mDepartment;
    private Employee manager;

    @Autowired
    DepartmentRepository dptRepository;

	@Autowired
	EmployeeRepository repository;

	@Before
	public void before() throws Exception {
        this.mDepartment = Entities.department();
        this.dptRepository.save(this.mDepartment);

		this.employee = Entities.employee();
        this.employee.setDepartment(this.mDepartment);
		this.employee = this.repository.save(employee);

	}

	@Test
	public void created() {
		assertNotNull(this.employee);
		assertNotNull(this.employee.getId());
	}

	@Test(expected = DataIntegrityViolationException.class)
         public void duplicateName() throws Exception {
        Employee employee1 = Entities.employee();
        employee1.setSkypeName(this.employee.getSkypeName());
        this.repository.save(employee1);
    }

	@Test
	public void findAll_notNull() throws Exception {
		System.out.println(this.repository.toString());
		List<Employee> employees = this.repository.findAll();
		assertNotNull(employees);
		assertTrue(0 < employees.size());
	}

	@Test
	public void findByDeptId() throws Exception {
        Employee employee1 = this.repository.findOne(this.employee.getId());
		assertNotNull(employee1);
		assertEquals(this.employee.getSkypeName(), employee1.getSkypeName());
		assertNotNull(this.employee.getDepartment());
	}

	@Test
	public void findById_notPresent() throws Exception {
        Employee employee = this.repository.findOne(NOT_PRESENT_ID);
		assertNull(employee);
	}

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void findById_null() throws Exception {
        Employee employee1 = this.repository.findOne(null);
        assertNull(employee1);
    }

	@Test
	public void findByName() throws Exception {
        Employee employee1 = this.repository.findByFirstName(this.employee.getFirstName());
		assertNotNull(employee1);
		assertEquals(this.employee.getFirstName(), employee1.getFirstName());
        assertEquals(this.employee.getLastName(), employee1.getLastName());
        assertEquals(this.employee.getSkypeName(), employee1.getSkypeName());

		assertNotNull(this.employee.getDepartment());
	}

	@Test
	public void findByName_null() throws Exception {
		Employee employee1 = this.repository.findByFirstName(NOT_PRESENT_VALUE);
		assertNull(employee1);
	}

//	@Test
//	public void findByParentDeptId() throws Exception {
//		List<Department> depts = this.repository
//				.findByParentDepartmentId(this.department.getParentDepartment()
//						.getId());
//		assertNotNull(depts);
//		assertEquals(1, depts.size());
//		Department dept = depts.get(0);
//		assertEquals(this.department.getName(), dept.getName());
//		assertNotNull(this.department.getParentDepartment());
//	}
//
//	@Test
//	public void findByParentDeptId_unknowId() throws Exception {
//		List<Department> depts = this.repository
//				.findByParentDepartmentId(random.nextInt());
//		assertNotNull(depts);
//		assertEquals(0, depts.size());
//	}
//
	@Test
	public void update() throws Exception {
		Employee employee1 = this.repository.findByFirstName(this.employee.getFirstName());
        employee1.setFirstName(SOME_NEW_NAME);
		this.repository.saveAndFlush(employee1);

        employee1 = null;
        employee1 = this.repository.findByFirstName(SOME_NEW_NAME);
		assertNotNull(employee1);
		assertEquals(SOME_NEW_NAME, employee1.getFirstName());
	}
}
