package com.systemsinmotion.orgchart.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;

public class DefaultControllerTest {

	@Autowired
	private DepartmentService mockDepartmentService;

	@Autowired
	private Department mockDepartment;

	// Map model = new HashMap<String, Object>();

	@SuppressWarnings("unused")
	private static final String DEPARTMENT_LIST_MISSING_ERROR = "Expected Model to contain a List of Departments, but did not.";

}
