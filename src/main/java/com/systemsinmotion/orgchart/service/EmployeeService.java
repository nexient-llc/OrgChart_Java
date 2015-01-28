package com.systemsinmotion.orgchart.service;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	

	public List<Employee> findAllEmployees() {
		return this.repository.findAll();
	}
	
	public List<Employee> findEmployeeByFirstName(String firstName){
		return this.repository.findByFirstName(firstName);
	}
	
	public List<Employee> findEmployeeByLastName(String lastName){
		return this.repository.findByLastName(lastName);
	}
	
	public void setRepository(EmployeeRepository repository){
		this.repository = repository;
	}

	public Employee findEmployeeByID(Integer employeeId) {
		return this.repository.findOne(employeeId);
		
	}

	public Employee storeEmployee(Employee mockEmployee) {
		return this.repository.save(mockEmployee);
	}
	
	public List<Employee> findDistinctEmployeeByDepartment(Department department){
		return this.repository.findDistinctEmployeeByDepartment(department);
	}
	
	public List<Employee> findDistinctEmployeeByJobTitle(JobTitle jobTitle){
		return this.repository.findDistinctEmployeeByJobTitle(jobTitle);
	}
	
	public List<Employee> findDistinctEmployeeByFirstNameOrLastNameAndDepartmentAndJobTitle(String firstName, String lastName, Department department, JobTitle jobTitle){
		return this.repository.findDistinctEmployeeByFirstNameOrLastNameAndDepartmentAndJobTitle(firstName, lastName, department, jobTitle);
	}
	
	public List<Employee> findDistinctEmployeeByFirstNameOrLastNameAndDepartment(String firstName, String lastName, Department department){
		return this.repository.findDistinctEmployeeByFirstNameOrLastNameAndDepartment(firstName, lastName, department);
	}
	
	public List<Employee> findDistinctEmployeeByFirstNameOrLastNameAndJobTitle(String firstName, String lastName, JobTitle jobTitle){
		return this.repository.findDistinctEmployeeByFirstNameOrLastNameAndJobTitle(firstName, lastName, jobTitle);
	}
	
	public List<Employee> findDistinctEmployeeByDepartmentAndJobTitle(Department department, JobTitle jobTitle){
		return this.repository.findDistinctEmployeeByDepartmentAndJobTitle(department, jobTitle);
	}
	
	public List<Employee> findDistinctEmployeeByFirstNameOrLastName(String firstName, String lastName){
		return this.repository.findDistinctEmployeeByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCase(firstName, lastName);
	}
	
	// Function that gets employees for the autocompletion
	public List<Employee> getEmployeeSuggestions(String name) {
		String nameArr[] = name.split(" ");
		List<Employee> employees = null;
		
		if(nameArr.length == 1) {
			employees = this.repository.findDistinctEmployeeByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCase(nameArr[0], nameArr[0]);
		}
		else if(nameArr.length == 2) {
			employees = this.repository.findDistinctEmployeeByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCase(nameArr[0], nameArr[1]);
		}
		
		if(employees == null || employees.size() == 0) {
			employees = null;
		}
		return employees;
	}
	
	
	
}
	