package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	public List<Employee> findAllEmployees() {
		return this.repository.findAll();
	}

	public void setRepository(EmployeeRepository repository) {
		this.repository = repository;
	}

	public Employee findEmployeeByID(Integer id) {
		return this.repository.findOne(id);
	}

	@Transactional
	private Employee storeEmployee(Employee employee) {
		return repository.save(employee);
	}

	@Transactional
	public Employee saveEmployee(Employee employee) {
		employee.setIsActive(true);
		return storeEmployee(employee);
	}

	public void removeEmployee(Employee employee) {
		employee.setIsActive(false);
		storeEmployee(employee);
	}

	public List<Employee> findAllActiveEmployees() {
		return this.repository.findByIsActiveIsTrue();

	}
}
