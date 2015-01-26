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

	public Employee findEmployeeById(Integer employeeId) {
		return this.repository.findById(employeeId);
	}
	
	public Employee findEmployeeByFirstName(String firstName) {
		return this.repository.findByFirstName(firstName);
	}
	
	public Employee findEmployeeByLastName(String lastName) {
		return this.repository.findByLastName(lastName);
	}
	
	public List<Employee> findEmployeeByFirstNameOrLastName(String firstName, String lastName) {
		return this.repository.findByFirstNameOrLastName(firstName, lastName);
	}
	
	public List<Employee> findEmployeeByDepartmentId(Integer deptId) {
		return this.repository.findByDepartmentId(deptId);
	}
	
	public List<Employee> findEmployeeByJobTitleId(Integer jobId) {
		return this.repository.findByJobTitleId(jobId);
	}

	public Employee storeEmployee(Employee mockEmployee) {
		return this.repository.save(mockEmployee);
	}
	
	public List<Employee> findEmployeeByFirstNameOrLastNameAndDepartmentId(String firstName, String lastName, Integer deptId) {
		return this.repository.findByFirstNameOrLastNameAndDepartmentId(firstName, lastName, deptId);
	}
	
	public List<Employee> findEmployeeByFirstNameOrLastNameAndJobTitleId(String firstName, String lastName, Integer jobId) {
		return this.repository.findByFirstNameOrLastNameAndJobTitleId(firstName, lastName, jobId);
	}
	
	public List<Employee> findEmployeeByDepartmentIdAndJobTitleId(Integer deptId, Integer jobId) {
		return this.repository.findByDepartmentIdAndJobTitleId(deptId, jobId);
	}
	
	public List<Employee> findEmployeeByFirstNameOrLastNameAndDepartmentIdAndJobTitleId(String firstName, String lastName, Integer deptId, Integer jobId) {
		return this.repository.findByFirstNameOrLastNameAndDepartmentIdAndJobTitleId(firstName, lastName, deptId, jobId);
	}
}
