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
	
	public List<Employee> findByAllPartialName(String firstName, String lastName, Department department, JobTitle jobTitle){
		return this.repository.findByAllPartialName(department.getId(), jobTitle.getId(), firstName, lastName);
	}
	
	public List<Employee> findDistinctEmployeeByFirstNameOrLastNameAndDepartment(String firstName, String lastName, Department department){
		return this.repository.findDistinctEmployeeByDepartmentAndFirstNameOrDepartmentAndLastName(department, firstName, department, lastName);
	}
	
	public List<Employee> findDistinctEmployeeByFirstNameOrLastNameAndJobTitle(String firstName, String lastName, JobTitle jobTitle){
		return this.repository.findDistinctEmployeeByJobTitleAndFirstNameOrLastName(firstName, lastName, jobTitle);
	}
	
	public List<Employee> findDistinctEmployeeByDepartmentAndJobTitle(Department department, JobTitle jobTitle){
		return this.repository.findDistinctEmployeeByDepartmentAndJobTitle(department, jobTitle);
	}
	
	public List<Employee> findDistinctEmployeeByFirstNameOrLastName(String firstName, String lastName){
		return this.repository.findDistinctEmployeeByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCase(firstName, lastName);
	}

	public List<Employee> findDistinctEmployeeByFirstNameAndLastName(String firstName, String lastName) {
		return this.repository.findDistinctEmployeeByFirstNameAndLastName(firstName, lastName);
	}

	public List<Employee> findDistinctEmployeeByFirstNameAndLastNameAndJobTitle(String firstName, String lastName, JobTitle jobTitle) {
		return this.repository.findDistinctEmployeeByFirstNameAndLastNameAndJobTitle(firstName, lastName, jobTitle);
	}

	public List<Employee> findDistinctEmployeeByFirstNameAndLastNameAndDepartment(String firstName, String lastName, Department department) {
		return this.repository.findDistinctEmployeeByFirstNameAndLastNameAndDepartment(firstName, lastName, department);
	}

	public List<Employee> findDistinctEmployeeByFirstNameAndLastNameAndDepartmentAndJobTitle(String firstName, String lastName, Department department,JobTitle jobTitle) {
		return this.repository.findDistinctEmployeeByFirstNameAndLastNameAndDepartmentAndJobTitle(firstName, lastName, department, jobTitle);
	}
	
	/**
	 * Function that gets the employees for auto completion
	 * @param name
	 * @return
	 */
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
	
	/**
	 * Employs an employee search on the database based on what fields the search
	 * employee that is passed in has set.
	 * @param employee
	 * @return
	 */
	public List<Employee> employeeSearch(Employee employee){
		
		List<Employee> employees;	
		
		int setFlags = getEmployeeFlags(employee);
		
		switch(setFlags){
			case 1:{
				employees = this.findDistinctEmployeeByFirstNameOrLastName(employee.getFirstName(), employee.getFirstName());
				break;
			} case 3:{
				employees = this.findDistinctEmployeeByFirstNameAndLastName(employee.getFirstName(), employee.getLastName());
				break;
			} case 4:{
				employees = this.findDistinctEmployeeByDepartment(employee.getDepartment());
				break;
			} case 5:{
				employees = this.findDistinctEmployeeByFirstNameOrLastNameAndDepartment(employee.getFirstName(), employee.getFirstName(), employee.getDepartment());														
				break;
			} case 7:{
				employees = this.findDistinctEmployeeByFirstNameAndLastNameAndDepartment(employee.getFirstName(), employee.getLastName(), employee.getDepartment());
				break;
			} case 8:{
				employees = this.findDistinctEmployeeByJobTitle(employee.getJobTitle());
				break;
			} case 9:{
				employees = this.findDistinctEmployeeByFirstNameOrLastNameAndJobTitle(employee.getFirstName(), employee.getFirstName(), employee.getJobTitle());
				break;
			} case 11:{
				employees = this.findDistinctEmployeeByFirstNameAndLastNameAndJobTitle(employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
				break;
			} case 12:{
				employees = this.findDistinctEmployeeByDepartmentAndJobTitle(employee.getDepartment(), employee.getJobTitle());
				break;
			} case 13:{
				employees = this.findByAllPartialName(employee.getFirstName(), employee.getFirstName(), employee.getDepartment(), employee.getJobTitle());
				break;
			} case 15:{
				employees = this.findDistinctEmployeeByFirstNameAndLastNameAndDepartmentAndJobTitle(employee.getFirstName(), employee.getLastName(), employee.getDepartment(), employee.getJobTitle());
				break;
			} default:{ 
				employees = this.findAllEmployees();			
			}
		}
		return employees;
	}
	
	/**
	 * Sets the binary flags of whether an employee has certain fields not set to null or not and returns
	 * the appropriate number.
	 * @param employee
	 * @return
	 */
	public int getEmployeeFlags(Employee employee){
		int setFlags = 0;
		
		if(employee.getFirstName() != null){
			setFlags = setFlags ^ 1;
		}		
		if(employee.getLastName() != null){
			setFlags = setFlags ^ 2;
		}		
		if(employee.getDepartment() != null){
			setFlags = setFlags ^ 4;
		}
		if(employee.getJobTitle() != null){
			setFlags = setFlags ^ 8;
		}
		if(employee.getEmail() != null){
			setFlags = setFlags ^ 16;
		}
		if(employee.getSkypeName() != null){
			setFlags = setFlags ^ 32;
		}
		return setFlags;
	}
	
}
	