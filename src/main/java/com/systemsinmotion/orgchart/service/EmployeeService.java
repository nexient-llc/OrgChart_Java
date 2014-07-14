package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Employee findEmployeeByID(Integer employeeId) {
		return this.repository.findOne(employeeId);
	}

	public Employee storeEmployee(Employee employee) {
		if (employee.getIsActive() == null)
			employee.setIsActive(true);
		return this.repository.save(employee);
	}
	
	public void removeEmployee(Employee employee) {
		employee.setIsActive(false);
		this.repository.save(employee);
	}
	
	public List<Employee> findEmployeesByJobTitle(JobTitle jobTitle) {
		return this.repository.findByJobTitle(jobTitle);
	}
	
	public List<Employee> findEmployeesByFirstName(String firstName) {
		return this.repository.findByFirstNameContainingIgnoreCase(firstName);
	}
	
	public List<Employee> findEmployeesByLastName(String lastName) {
		return this.repository.findByLastNameContainingIgnoreCase(lastName);
	}

	public List<Employee> findEmployeesByDepartment(Department department) {
		return this.repository.findByDepartment(department);
	}
	
	public String putCommaDelimitersInAListOfEmployees(List<Employee> employees) {
		String output = new String();
		for (Employee emp : employees)
			output += emp.getFirstName() + " " + emp.getLastName() + ",";
		if (output.length() > 0)
			output = output.substring(0,output.length() - 1);
		return output;
	}

	public List<Employee> findAllActiveEmployees()
	{
		return this.repository.findByIsActiveIsTrue();
	}
}
