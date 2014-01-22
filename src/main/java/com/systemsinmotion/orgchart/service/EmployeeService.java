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
	
	public List<Employee> findAllActiveEmployees() {
		return this.repository.findByIsActiveTrue();
	}
	
	public void removeEmployee(Employee employee) {
		employee.setIsActive(false);
		this.repository.save(employee);
	}
	
	public List<Employee> findEmployeesByDepartment(Department department) {
		return this.repository.findByDepartmentId(department.getId());
	}
	
	public void setRepository(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	public Employee storeEmployee(Employee employee) {
		return this.repository.saveAndFlush(employee);
	}

	public Employee findEmployeeByEmail(String email){
		return this.repository.findByEmail(email);
	}

	public Employee findEmployeeById(Integer id) {
		return this.repository.findById(id);
	}

	public List<Employee> findEmployeesByManagerId(Integer managerId) {
		return this.repository.findByManagerId(managerId);
	}

	public List<Employee> findEmployeesByName(String firstName, String lastName){
		return this.repository.findByFirstNameOrLastNameAllIgnoreCase(firstName, lastName);
	}
	
	public List<Employee> findEmployeesByJobTitleId(Integer jobTitleId) {
		return this.repository.findByJobTitleId(jobTitleId);
	}
	
	public List<Employee> findEmployeesByFiltering(String fullName, Department dept, JobTitle job){
		String firstName = null;
		String lastName = null;
		String[] tokens = null;
		if (fullName != null) {
			tokens = fullName.split(" ", 2);

			if (tokens.length == 1) {
				firstName = lastName = tokens[0];
			} else if (tokens.length == 2) {
				firstName = tokens[0];
				lastName = tokens[1];
			}
		}
		
		int switchNum = 0;
		if (firstName.length() != 0 || lastName.length()!= 0)  switchNum +=4;
		if (job != null) switchNum +=2;
		if (dept != null) switchNum +=1;
		switch (switchNum){
		case 0:
			return this.repository.findAll();
		case 1:
			return this.repository.findByDepartment(dept);
		case 2:
			return this.repository.findByJobTitle(job);
		case 3:
			return this.repository.findByJobTitleAndDepartment(job, dept);
		case 4:
			return this.repository.findByFirstNameOrLastNameAllIgnoreCase(firstName, lastName);
		case 5:
			return this.repository.findByDepartmentAndFirstNameOrLastName(dept, firstName, lastName);
		case 6:
			return this.repository.findByJobTitleAndFirstNameOrLastName(job, firstName, lastName);
		case 7:
			return this.repository.findByDepartmentAndJobTitleAndFirstNameOrLastName(dept, job, firstName, lastName);
		}
		
		
		
		return null;
	}
	
}
