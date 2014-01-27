package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	public List<Employee> findAllEmployees() {
		return this.employeeRepo.findAll();
	}

	public Employee findEmployeeById(Integer employeeId) {
		return this.employeeRepo.findOne(employeeId);
	}
	
	/*
	public Employee findEmployeeByFirstName(String firstName) {
		return this.employeeRepo.findEmployeeByFirstName(firstName);
	}
	
	public Employee findEmployeeByLastName(String lastName) {
		return this.employeeRepo.findEmployeeByLastName(lastName);
	}
	*/
	
	public Employee findEmployeeByEmail(String email) {
		return this.employeeRepo.findEmployeeByEmail(email);
	}
	
	public Employee findEmployeeBySkypeName(String skypeName) {
		return this.employeeRepo.findEmployeeBySkypeName(skypeName);
	}
	
	public List<Employee> findEmployeesByIsActiveTrueOrderByFirstNameAsc() {
		return this.employeeRepo.findEmployeesByIsActiveTrueOrderByFirstNameAsc();
	}
	
	public void setRepository(EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	public Employee storeEmployee(Employee employee) {
		return this.employeeRepo.save(employee);
	}
}
