package com.systemsinmotion.orgchart;

import java.util.Random;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

//David Jones Testing New Branch

@SuppressWarnings("unused")
public class Entities {

	public static final String DEPARTMENT_NAME = "Department";
	public static final Integer DEPT_ID = 22;
	public static final String EMAIL = "email";
	public static final Integer EMPLOYEE_ID = 5;
	public static final String FIRST_NAME = "first name";
	public static final String JOB_TITLE = "Job Title";
	public static final Integer JOB_TITLE_ID = 5;
	public static final String JOB_TITLE_NAME = "JobTitle";
	public static final String LAST_NAME = "last name";
	public static final Integer MANAGER_ID = 1;
	public static final String SKYPE_NAME = "skype name";

	private static Random random = new Random();

	public static Department department() {
		Department department = new Department();
		department.setName(departmentName());
		return department;
	}

	public static Department department(Department parent) {
		Department department = new Department();
		department.setName(departmentName());
		department.setParentDepartment(parent);
		return department;
	}

	private static String departmentName() {
		return DEPARTMENT_NAME + random.nextInt();
	}

	public static JobTitle jobTitle() {
		JobTitle jobTitle = new JobTitle();
		jobTitle.setName(jobName());
		return jobTitle;
	}
	
	private static String jobName(){
		return JOB_TITLE_NAME + random.nextInt();
	}

	public static Employee employee() {
		Employee employee = new Employee();
		employee.setFirstName(randomEmployeeFirstName());
		employee.setLastName(randomEmployeeLastName());
		employee.setEmail(EMAIL + random.nextInt());
		return employee;
	}

	private static String randomEmployeeFirstName(){
		return FIRST_NAME + random.nextInt();
	}
	
	private static String randomEmployeeLastName(){
		return LAST_NAME + random.nextInt();
	}

	public static Employee manager() {
		Employee aManager = new Employee();
		aManager.setFirstName(randomEmployeeFirstName());
		aManager.setLastName(randomEmployeeLastName());
		aManager.setEmail(EMAIL + random.nextInt());
		aManager.setIsManager(true);
		return aManager;
		
	}
}
