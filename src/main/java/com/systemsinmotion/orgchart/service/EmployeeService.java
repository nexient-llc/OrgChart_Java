package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("employeeService")
@Transactional(readOnly = true)
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	public List<Employee> findAllEmployees() {
		return this.repository.findAll();
	}

	public Employee findEmployeeByID(Integer employeeId) {
		return this.repository.findOne(employeeId);
	}

	@Transactional
	public Employee storeEmployee(Employee employee) {
		if (employee.getIsActive() == null)
			employee.setIsActive(true);
		return this.repository.save(employee);
	}

	@Transactional
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
	
	public List<Employee> findAllActiveEmployees()
	{
		return this.repository.findByIsActiveIsTrue();
	}

	public String putCommaDelimitersInAListOfEmployees(List<Employee> employees) {
		String output = new String();
		for (Employee emp : employees)
			output += emp.getFirstName() + " " + emp.getLastName() + ",";
		if (output.length() > 0)
			output = output.substring(0,output.length() - 1);
		return output;
	}

	public List<Employee> findEmployeesByFilter(String firstName, String lastName,
			Department department, JobTitle jobTitle) {

		int vector = 0;
		if (firstName != null)
			vector |= 1; // First Bit
		if (lastName != null)
			vector |= 2; // Second Bit
		if (department != null)
			vector |= 4; // Third Bit
		if (jobTitle != null)
			vector |= 8; // Fourth Bit

		switch (vector)
		{
		case 1: // First Name
			return repository.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(firstName, firstName);
		case 2: // Last Name
			return repository.findByFirstNameContainingIgnoreCaseAndIsActiveIsTrueOrLastNameContainingIgnoreCaseAndIsActiveIsTrue(lastName, lastName);
		case 3: // First Name, Last Name
			return repository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndIsActiveIsTrue(firstName, lastName);
		case 4: // Department
			return repository.findByDepartmentAndIsActiveIsTrue(department);
		case 5: // First Name, Department
			return repository.findByUpperCaseNameAndDepartmentAndActive(firstName.toUpperCase(), department);
		case 6: // Last Name, Department
			return repository.findByUpperCaseNameAndDepartmentAndActive(lastName.toUpperCase(), department);
		case 7: // First Name, Last Name, Department
			return repository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentAndIsActiveIsTrue(firstName, lastName, department);
		case 8: // JobTitle
			return repository.findByJobTitleAndIsActiveIsTrue(jobTitle);
		case 9: // First Name JobTitle
			return repository.findByNameAndJobTitleAndActive(firstName.toUpperCase(), jobTitle);
		case 10: // Last Name, JobTitle
			return repository.findByNameAndJobTitleAndActive(lastName.toUpperCase(), jobTitle);
		case 11: // First Name, Last Name, JobTitle
			return repository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndJobTitleAndIsActiveIsTrue(firstName, lastName, jobTitle);
		case 12: // Department, JobTitle
			return repository.findByDepartmentAndJobTitleAndIsActiveIsTrue(department, jobTitle);
		case 13: // First Name, Department, JobTitle
			return repository.findByUpperCaseNameAndDepartmentAndJobTitle(firstName.toUpperCase(), department, jobTitle);
		case 14: // Last Name, Department, JobTitle
			return repository.findByUpperCaseNameAndDepartmentAndJobTitle(lastName.toUpperCase(), department, jobTitle);
		case 15: // First Name, Last Name, Department, JobTitle
			return repository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndDepartmentAndJobTitleAndIsActiveIsTrue(firstName, lastName, department, jobTitle);
		case 0:
		default:
			return findAllActiveEmployees();
		}
	}
}
