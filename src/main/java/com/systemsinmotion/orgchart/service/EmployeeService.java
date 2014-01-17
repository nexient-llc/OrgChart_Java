package com.systemsinmotion.orgchart.service;

import java.util.Collection;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	public Employee storeEmployee(Employee employee){
		Department dept = employee.getDepartment();
		if(dept != null)
			employee.setDepartment(dept.getId() == null ? null : dept);
		JobTitle job = employee.getJobTitle();
		if(job != null)
			employee.setJobTitle(job.getId() == null ? null : job);
		
		try{
			return this.repository.save(employee);
		}
		catch(ConstraintViolationException e){
			return null;
		}
		catch(DataIntegrityViolationException e){
			return null;
		}
	}
	
	public Employee findEmployeeById(Integer id){
		return this.repository.findById(id);
	}
	
	public Employee findEmployeeByEmail(String email){
		return this.repository.findByEmail(email);
	}
	
	public List<Employee> findEmployeesByManagerId(Integer managerId){
		return this.repository.findByManagerId(managerId);
	}
	
	public List<Employee> findEmployeesByDepartmentId(Integer departmentId){
		return this.repository.findByDepartmentId(departmentId);
	}
	
	public List<Employee> findAllEmployees(){
		return this.repository.findAll();
	}

	public void setRepository(EmployeeRepository repository) {
		this.repository = repository;		
	}
	
	public Employee findEmployeeByFirstAndLastName(String firstName, String lastName){
		return this.repository.findByFirstNameAndLastName(firstName, lastName);
	}

	public void setEmployeeInactive(Employee employee) {
		employee.setIsActive(false);
		storeEmployee(employee);	
	}

	public Employee findEmployeeByFirstName(String firstName) {
		return this.repository.findByFirstName(firstName);
	}

	public List<Employee> findEmployeesByJobTitleId(Integer jobTitleId) {
		return this.repository.findByJobTitleId(jobTitleId);
	}

	public List<Employee> findEmployeesLikeFirstOrLastName(String firstName, String lastName) {
		return this.repository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(firstName, lastName);
	}

	public List<Employee> findActiveEmployees() {
		return this.repository.findByIsActiveTrue();
	}
}
