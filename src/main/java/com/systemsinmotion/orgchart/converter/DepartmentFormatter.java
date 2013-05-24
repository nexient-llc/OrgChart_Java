package com.systemsinmotion.orgchart.converter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.service.DepartmentService;

@Component
public class DepartmentFormatter implements Formatter<Department> {

	@Autowired
	private DepartmentService departmentService;

	@Override
	public String print(Department department, Locale arg1) {
		return department.getName();
	}

	@Override
	public Department parse(String departmentId, Locale arg1) throws ParseException {
		return departmentService.findDepartmentByID(Integer.parseInt(departmentId));
	}

}