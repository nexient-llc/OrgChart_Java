package com.systemsinmotion.orgchart;

import java.util.Random;

import com.systemsinmotion.orgchart.entity.Department;

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

	public static Department department() {
//		Department department = new Department(null, null, departmentName(), null, null);
		Department department = new Department(departmentName());
		department.setDepartmentId(r.nextInt());
		return department;
	}

	public static Department department(Department parent) {
//		Department department = new Department(parent, manager(), departmentName(), null, null);
		Department department = new Department(departmentName());
		department.setDepartmentId(r.nextInt());
		return department;
	}

	private static String departmentName() {
		return DEPARTMENT_NAME + r.nextInt();
	}

}
