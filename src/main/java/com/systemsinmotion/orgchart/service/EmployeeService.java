package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	public List<Employee> findAllEmployees() {
		return this.repository.findAll();
	}

	public Employee findEmployeeByID(Integer employeeId) {
		return this.repository.findOne(employeeId);
	}

	public void removeEmployee(Employee employee) {
		this.repository.delete(employee);
	}

	public void setRepository(EmployeeRepository repository) {
		this.repository = repository;
	}

	public Employee storeEmployee(Employee employee) {
		return this.repository.save(employee);
	}

}