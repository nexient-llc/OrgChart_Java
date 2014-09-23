package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.data.EmployeeRepository;

@Service("employeeService")
public class EmployeeService{

	@Autowired
	EmployeeRepository repository;

	public List<Employee> findAllEmployees(){
		return this.repository.findAll();
	}
	
	public List<Employee> findAllActiveEmployees() {
		return this.repository.findByIsActiveIsTrue();
	}	
	
	public List<Employee> listByNameSubstring(String str){
		return this.repository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(str, str);
	}
	public Employee findById(Integer id) {
		return this.repository.findOne(id);
	}
	
	@Transactional
	public void removeEmployee(Employee employee) {
		this.repository.delete(employee);
	}	
	
	public void removeEmployee(Integer id) {
		this.repository.delete(id);
	}		

	@Transactional
	public Employee storeEmployee(Employee employee) {
		return this.repository.saveAndFlush(employee);
	}

	public Employee findEmployeeByID(Integer employeeId) {
		return this.repository.findOne(employeeId);
	}
	public long rowCount(){
		return this.repository.count();
	}
}
