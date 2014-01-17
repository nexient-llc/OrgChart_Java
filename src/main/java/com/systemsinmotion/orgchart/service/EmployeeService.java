package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.BaseEntity;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

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
	
	public Employee findByFirstNameAndLastName(String firstName, String lastName) {
		return this.repository.findByFirstNameAndLastName(firstName, lastName);
	}

	public List<Employee> findByJobTitle(JobTitle jobTitle) {
		return this.repository.findByJobTitle(jobTitle);
	}

	public Employee findByEmail(String email) {
		return this.repository.findByEmail(email);
	}

	public List<Employee> findByDepartment(Department department) {
		return this.repository.findByDepartment(department);
	}

	public BaseEntity storeEmployee(Employee employee) {
		return this.repository.save(employee);
	}
	
	public List<Employee> findLikeFirstNameOrLikeLastName(String name) {
	    return this.repository.findByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCaseOrderByFirstNameDesc(name, name);
	}
}
