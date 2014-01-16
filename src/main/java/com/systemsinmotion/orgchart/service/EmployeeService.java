package com.systemsinmotion.orgchart.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	public Employee storeEmployee(Employee employee){
		try{
			return this.repository.save(employee);
		}
		catch(ConstraintViolationException e){
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
}
