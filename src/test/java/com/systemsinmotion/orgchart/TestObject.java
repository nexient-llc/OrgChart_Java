package com.systemsinmotion.orgchart;

import java.util.Random;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

public class TestObject {

	public static final String JOB_TITLE = "Job Title";
	public static final String DEPARTMENT_NAME = "Department";
	public static final String SKYPE_NAME = "skype name";
	public static final String EMAIL = "email";
	public static final String LAST_NAME = "last name";
	public static final String FIRST_NAME = "first name";
	public static final Integer MANAGER_ID = 1;
	public static final Integer JOB_TITLE_ID = 5;
	public static final Integer EMPLOYEE_ID = 5;
	public static final Integer DEPT_ID = 22;
	private static Random r = new Random();

	public static Department department() 
	
	{
		Department department = new Department(null, null, departmentName(), null, null);
		department.setDepartmentId(r.nextInt());
		return department;
		
	}

	public static Department department(Department parent) 
	{
		Department department = new Department(parent, manager(), departmentName(), null, null);
		department.setDepartmentId(r.nextInt());
		return department;
		
	}

	private static String departmentName() 
	{
		
		return DEPARTMENT_NAME + r.nextInt();
		
	}
	
	public static Employee employee() 
	{
		
		Employee emp = new Employee(r.nextInt(),FIRST_NAME, LAST_NAME, EMAIL, SKYPE_NAME, false,null, department(), null,null, null);
		emp.setEmpID(r.nextInt());
		return emp;

	}  
	
	public static Employee manager() 
	{
		
		Employee mgr = new Employee(r.nextInt(), FIRST_NAME, LAST_NAME, EMAIL + r.nextInt(), SKYPE_NAME + r.nextInt(), true, null, null, null, null, null);
		mgr.setEmpID(MANAGER_ID);
		return mgr;
		
	}

	public static JobTitle jobTitle() 
	{
		
		JobTitle jobTitle = new JobTitle(JOB_TITLE);
		jobTitle.setJobTitleID(r.nextInt());
		return jobTitle;
		
	}

}
