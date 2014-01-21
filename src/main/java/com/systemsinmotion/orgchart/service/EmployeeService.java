package com.systemsinmotion.orgchart.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		return this.employeeRepository.saveAndFlush(employee);
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
	
	public List<Employee> findByDepartmentID(Integer id) {
		return this.employeeRepository.findByDepartmentId(id);
	}

	public Employee findByEmail(String email) {
		return this.employeeRepository.findByEmail(email);
	}
	
	public List<Employee> findByJobTitle(JobTitle jobTitle) {
		return this.employeeRepository.findByJobTitle(jobTitle);
	}

	public List<Employee> findAllActiveEmployees() {
		return this.employeeRepository.findByIsActiveTrue();
	}
	
	public List<Employee> findByJobTitleID(Integer id) {
		return this.employeeRepository.findByJobTitleId(id);
	}
	
	public List<Employee> findByFirstNameContains(String name){
		return this.employeeRepository.findByFirstNameContaining(name);
	}
	
	public List<Employee> findByLastNameContains(String name){
		return this.employeeRepository.findByLastNameContaining(name);
	}
	
	public List<Employee> filterEmployee(String name, Integer departmentID, Integer jobTitleID){
		String[] nameComponents = name.split(" ");
		Set<Employee> processingEmployees = new HashSet<Employee>();
		List<Employee> filteredEmployees = new ArrayList<Employee>();
		if(!name.equals(null)){
			for(String nameComp : nameComponents) {
				processingEmployees.addAll(findByFirstNameContains(nameComp));
				processingEmployees.addAll(findByLastNameContains(nameComp));
			}
		}
		else if(departmentID != null) {
			processingEmployees.addAll(findByDepartmentID(departmentID));
		}
		else if(jobTitleID != null) {
			processingEmployees.addAll(findByJobTitleID(jobTitleID));
		}
		
		for(Employee test : processingEmployees){
			if(test.getDepartment().getId().equals(departmentID) || departmentID == null){
				if(test.getJobTitle().getId().equals(jobTitleID) || jobTitleID == null){
					filteredEmployees.add(test);
				}
			}
		}
		
		return filteredEmployees;
	}

	public void setEmployeeInactive(Integer id) {
		Employee removeEmployee = findEmployeeByID(id);
		removeEmployee.setIsActive(false);
		storeEmployee(removeEmployee);
	}

}
