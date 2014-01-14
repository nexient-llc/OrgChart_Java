package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	public List<Employee> findAllEmployees() {
		return this.employeeRepo.findAll();
	}

	public Employee findEmployeeByID(Integer Id) {
		return this.employeeRepo.findById(Id);
	}

	public Employee storeEmployee(Employee mockEmployee) {
		return this.employeeRepo.save(mockEmployee);
	}

	public Employee findEmployeeByFirstName(String firstName) {
		return this.employeeRepo.findByFirstName(firstName);
	}

}
