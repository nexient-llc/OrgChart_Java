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
	
	public void removeEmployee(Employee employee) {
		this.repository.delete(employee);
	}
	
	public List<Employee> findEmployeesByDepartment(Department department) {
		return this.repository.findByDepartmentId(department.getId());
	}
	
	public void setRepository(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	public Employee storeEmployee(Employee employee) {
		return this.repository.save(employee);
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

	
	
	
}
