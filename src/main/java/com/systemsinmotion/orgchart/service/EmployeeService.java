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
	
	public List<Employee> findAllEmployees() {
		return this.repository.findAll();
	}

	public Employee findEmployeeByID(Integer employeeId) {
		return this.repository.findById(employeeId);
	}

	public Employee storeEmployee(Employee mockEmployee) {
		return this.repository.save(mockEmployee);
	}

	public List<Employee> findAllActiveEmployees() {
		return this.repository.findByIsActiveIsTrue();
	}

	public List<Employee> findByIsManager(boolean isManager){
		return this.repository.findByIsManager(isManager);
	}
	
}
