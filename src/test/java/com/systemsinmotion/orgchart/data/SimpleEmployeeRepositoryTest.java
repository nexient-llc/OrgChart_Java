package com.systemsinmotion.orgchart.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.Entities;
import com.systemsinmotion.orgchart.config.JPAConfig;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.SimpleEmployee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfig.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class SimpleEmployeeRepositoryTest {

	private SimpleEmployee simpleEmployee;
	private SimpleEmployee inactiveSimpleEmployee;
	
	@Autowired
	SimpleEmployeeRepository repository; 
	
	@Before
	public void before() {
		simpleEmployee = Entities.simpleEmployee();
		inactiveSimpleEmployee = Entities.simpleEmployee2();
		inactiveSimpleEmployee.setIsActive(false);
		this.simpleEmployee.setId(this.repository.save(this.simpleEmployee).getId());
		this.inactiveSimpleEmployee.setId(this.repository.save(this.inactiveSimpleEmployee).getId());
	}
	
	@Test
	public void init() {
		assertNotNull(repository);
	}
	
	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void duplicateEmployee_null() throws Exception {
		SimpleEmployee emp = null;
		this.repository.save(emp);
	}
	@Test
	public void findAll() {
		List<SimpleEmployee> emps = this.repository.findAll();
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}

	@Test
	public void findById() throws Exception {
		SimpleEmployee emp = this.repository.findById(this.simpleEmployee.getId());
		assertNotNull("Expecting a non-null Employee but was null", emp);
		assertEquals(this.simpleEmployee.getFirstName(), emp.getFirstName());
		assertEquals(this.simpleEmployee.getLastName(), emp.getLastName());
	}
	
	@Test
	public void findById_null() throws Exception {
		SimpleEmployee emp = this.repository.findById(null);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test
	public void findById_XXX() throws Exception {
		SimpleEmployee emp = this.repository.findById(Entities.NOT_PRESENT_ID);
		assertNull("Expecting a null Employee but was non-null", emp);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue_nulls() throws Exception {
		this.repository.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(null, null);
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue_firstName() throws Exception {
		List<SimpleEmployee> emps = this.repository.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.NOT_PRESENT_VALUE);
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue_lastName() throws Exception {
		List<SimpleEmployee> emps = this.repository.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.NOT_PRESENT_VALUE, Entities.LAST_NAME);
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue_firstNameAndLastName() throws Exception {
		List<SimpleEmployee> emps = this.repository.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME);
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue_XXX() throws Exception {
		List<SimpleEmployee> emps = this.repository.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.NOT_PRESENT_VALUE, Entities.NOT_PRESENT_VALUE);
		assertNotNull(emps);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue_ignoreUpperCase() throws Exception {
		List<SimpleEmployee> emps = this.repository.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME.toUpperCase(), Entities.LAST_NAME.toUpperCase());
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue_ignoreLowerCase() throws Exception {
		List<SimpleEmployee> emps = this.repository.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME.toLowerCase(), Entities.LAST_NAME.toLowerCase());
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}
	
	@Test
	public void findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue_inactive() throws Exception {
		List<SimpleEmployee> emps = this.repository.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME_2, Entities.LAST_NAME_2);
		assertNotNull(emps);
		assertTrue(emps.isEmpty());
	}


	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue_nulls() throws Exception {
		this.repository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(null, null);
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue_firstName() throws Exception {
		List<SimpleEmployee> emps = this.repository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.NOT_PRESENT_VALUE);
		assertNotNull(emps);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue_lastName() throws Exception {
		List<SimpleEmployee> emps = this.repository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.NOT_PRESENT_VALUE, Entities.LAST_NAME);
		assertNotNull(emps);
		assertTrue(emps.isEmpty());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue_firstNameAndLastName() throws Exception {
		List<SimpleEmployee> emps = this.repository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME, Entities.LAST_NAME);
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue_XXX() throws Exception {
		List<SimpleEmployee> emps = this.repository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.NOT_PRESENT_VALUE, Entities.NOT_PRESENT_VALUE);
		assertNotNull(emps);
		assertTrue(emps.isEmpty());
	}
	
	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue_ignoreUpperCase() throws Exception {
		List<SimpleEmployee> emps = this.repository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME.toUpperCase(), Entities.LAST_NAME.toUpperCase());
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue_ignoreLowerCase() throws Exception {
		List<SimpleEmployee> emps = this.repository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME.toLowerCase(), Entities.LAST_NAME.toLowerCase());
		assertNotNull(emps);
		assertTrue(0 < emps.size());
	}

	@Test
	public void findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue_inactive() throws Exception {
		List<SimpleEmployee> emps = this.repository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(Entities.FIRST_NAME_2, Entities.LAST_NAME_2);
		assertNotNull(emps);
		assertTrue(emps.isEmpty());
	}
}
