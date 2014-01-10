package com.systemsinmotion.orgchart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.EmployeeRepository;

@Service("employeeService")
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

}
