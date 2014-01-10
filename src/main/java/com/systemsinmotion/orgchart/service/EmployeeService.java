package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;

	public void setRepository(EmployeeRepository repository) {
		this.repository = repository;
	}

	public List<Employee> findAllEmployees() {
		return this.repository.findAll();
	}

	public Employee findByEmployeeId(Integer employeeId) {
		return this.repository.findOne(employeeId);
	}
}
