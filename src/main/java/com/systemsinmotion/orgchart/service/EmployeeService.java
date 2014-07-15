package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("EmployeeService")
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	public List<Employee> findAllEmployees() {
		// TODO Auto-generated method stub
		return this.repository.findAll();
	}

	public Employee findEmployeeByID(Integer employeeId) {
		// TODO Auto-generated method stub
		return this.repository.findOne(employeeId);
	}

	public Employee storeEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return this.repository.save(employee);
	}
	
	public List<Employee> employeesFoundByDepartmentName(String department){
		return this.repository.findByDepartmentName(department);
	}
	
	public List<Employee> employeesFoundByJobTitletName(String title){
		return this.repository.findByJobTitleName(title);
	}
	
	
	public List<Employee> employessFoundByDepartmentNameAndJobName(String department, String title){
		return this.repository.findByDepartmentNameAndJobTitleName(department, title);
	}
	
}
