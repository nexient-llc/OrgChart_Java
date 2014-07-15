package com.systemsinmotion.orgchart;

import java.util.Random;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

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

	// ****************Departments**************************
	public static Department department() {
		Department department = new Department();
		department.setName(departmentName());
		department.setIsActive(true);
		// department.setId(departmentId());
		return department;
	}

	public static Department department(Integer id) {
		Department department = department();
		department.setId(DEPT_ID);
		return department;
	}

	public static Department department(Department parent) {
		Department department = department();
		department.setParentDepartment(parent);
		return department;
	}

	private static String departmentName() {
		return DEPARTMENT_NAME + random.nextInt();
	}

	// private static Integer departmentId() {
	// return DEPT_ID + random.nextInt();
	// }

	// ****************Employees**************************
	public static Employee employee() {
		Employee emp = new Employee();
		emp.setDepartment(department());
		emp.setFirstName(FIRST_NAME);
		emp.setLastName(LAST_NAME);
		emp.setEmail(EMAIL + random.nextInt());
		emp.setSkypeName(SKYPE_NAME + random.nextInt());
		emp.setIsActive(true);
		// emp.setIsManager(false);
		return emp;
	}

	public static Employee employee(Department dept) {
		Employee emp = employee();
		emp.setDepartment(department());
		// emp.setIsManager(false);
		return emp;
	}

	public static Employee employee(Integer employeeId) {
		Employee emp = employee();
		emp.setId(EMPLOYEE_ID);
		return emp;
	}

	public static Employee manager() {
		Employee mgr = employee();
		mgr.setId(MANAGER_ID);
		mgr.setIsManager(true);
		return mgr;
	}

	// ****************Job Titles**************************
	public static JobTitle jobTitle() {
		JobTitle jobTitle = new JobTitle();
		jobTitle.setName(JOB_TITLE_NAME + random.nextInt());
		jobTitle.setId(JOB_TITLE_ID);
		jobTitle.setIsActive(true);
		return jobTitle;
	}

	public static JobTitle jobTitle(Integer jobTitleId) {
		JobTitle jobTitle = jobTitle();
		jobTitle.setId(JOB_TITLE_ID);
		return jobTitle;
	}

}
