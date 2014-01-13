package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepo;

	public void setEmployeeService(EmployeeRepository mockEmployeeRepo) {
		this.employeeRepo = mockEmployeeRepo;
	}

	public List<Employee> findAllEmployees() {
		return this.employeeRepo.findAll();
	}

	public Employee findEmployeeByID(Integer Id) {
		return this.employeeRepo.findById(Id);
	}

	public Employee storeEmployee(Employee mockEmployee) {
		return this.employeeRepo.save(mockEmployee);
	}

}
