package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.BaseEntity;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	public void setRepository(EmployeeRepository repository) {
		this.employeeRepository = repository;
	}
	
	public void removeEmployee(Employee employee) {
		this.employeeRepository.delete(employee);
	}

	public List<Employee> findAllEmployees() {
		return this.employeeRepository.findAll();
	}

	public Employee findEmployeeByID(Integer employeeId) {
		return this.employeeRepository.findById(employeeId);
	}

	public Employee storeEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	public Employee findEmployeeByName(String firstName, String lastName) {
		return this.employeeRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	public Employee findEmployeeByFirstName(String firstName) {
		return this.employeeRepository.findByFirstName(firstName);
	}

	public Employee findEmployeeByLastName(String lastName) {
		return this.employeeRepository.findByLastName(lastName);
	}

	public List<Employee> findByDepartment(Department department) {
		return this.employeeRepository.findByDepartment(department);
	}

	public Employee findByEmail(String email) {
		return this.employeeRepository.findByEmail(email);
	}

}
