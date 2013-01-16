package com.systemsinmotion.orgchart.dao;

//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import com.systemsinmotion.orgchart.TestObject;
//import com.systemsinmotion.orgchart.entity.Department;
//import com.systemsinmotion.orgchart.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class EmployeeDAOTest {

//	private static final String NOT_PRESENT_VALUE = "XXX";
//	private static final Integer NOT_PRESENT_ID = -666;
//	private Department department;
//	private Employee employee;
//	private Employee manager;
//
//	@Autowired
//	EmployeeDAO employeeDAO;
//
//	@Autowired
//	DepartmentDAO departmentDAO;
//	
//	@Before
//	public void before() throws Exception {
//		department = TestObject.department();
//		departmentDAO.save(department);
//
//		employee = TestObject.employee();
//		employee.setDept(department);
//		employee.setEmpID(employeeDAO.save(employee));
//	}
//
//	@After
//	public void after() {
//		employeeDAO.delete(employee);
//		departmentDAO.delete(department);
//
//		if (null != manager) {
//			employeeDAO.delete(manager);
//		}
//	}
//
//	@Test
//	@Rollback
//	public void findByEmployeeIDTest_null() throws Exception {
//		Employee emp = employeeDAO.findByEmployeeID(NOT_PRESENT_ID);
//		assertThat(emp, describedAs("null", is(nullValue())));
//		//assertNull("Expected null returned non-null", emp);
//	}
	
}
