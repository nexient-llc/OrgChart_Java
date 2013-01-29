package com.systemsinmotion.orgchart.service;

import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.systemsinmotion.orgchart.TestObject;
import com.systemsinmotion.orgchart.dao.DepartmentDAO;
import com.systemsinmotion.orgchart.entity.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class DepartmentServiceTest {
    
    @Autowired
    DepartmentService departmentService;

    DepartmentDAO mockDepartmentDAO = mock(DepartmentDAO.class);
    Department mockDepartment = mock(Department.class);
    
    private ArrayList<Department> listOfFoundDepts = new ArrayList<Department>(); 

    @Before
    public void before() throws Exception {
	when(mockDepartment.getDepartmentId()).thenReturn(TestObject.DEPT_ID);
	listOfFoundDepts.add(mockDepartment);
	when(mockDepartmentDAO.findAll()).thenReturn(listOfFoundDepts);
	when(mockDepartmentDAO.findById(TestObject.DEPT_ID)).thenReturn(mockDepartment);
	when(mockDepartmentDAO.save(mockDepartment)).thenReturn(TestObject.DEPT_ID);
	departmentService.setDepartmentDAO(mockDepartmentDAO);
    }
    
    @Test
    @Rollback
    public void shouldFindDepartmentbyID() {
	assertNotNull(departmentService.findDepartmentByID(TestObject.DEPT_ID));
	assertEquals(TestObject.DEPT_ID, departmentService.findDepartmentByID(TestObject.DEPT_ID).getDepartmentId());
    }
    
    @Test
    @Rollback
    public void shouldFindAllDepartments(){
	assertNotNull(departmentService.findAllDepartments());
    }
    
    @Test
    @Rollback
    public void shouldStoreDepartmentUsingDepartmentDAO(){
	assertNotNull(departmentService.storeDepartment(mockDepartment));
	assertEquals(TestObject.DEPT_ID, departmentService.storeDepartment(mockDepartment));
    }


}
