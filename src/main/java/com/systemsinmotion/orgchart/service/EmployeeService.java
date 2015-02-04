package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	public List<Employee> findAllEmployees() {
		return this.repository.findAll();
	}

	public void setRepository(EmployeeRepository repository) {
		this.repository = repository;
	}

	public Employee findEmployeeByID(Integer id) {
		return this.repository.findOne(id);
	}

	@Transactional
	private Employee storeEmployee(Employee employee) {
		return repository.save(employee);
	}

	@Transactional
	public Employee saveEmployee(Employee employee) {
		employee.setIsActive(true);
		return storeEmployee(employee);
	}

	public void removeEmployee(Employee employee) {
		employee.setIsActive(false);
		storeEmployee(employee);
	}

	public List<Employee> findAllActiveEmployees() {
		return this.repository.findByIsActiveIsTrue();
	}

	public List<Employee> findEmployeeByJobID(Integer id) {
		return this.repository.findByJobTitleId(id);
	}

	public List<Employee> findEmployeeByDepartmentID(Integer id) {
		return this.repository.findByDepartmentId(id);
	}

	public List<Employee> findEmployeeByDepartmentIDAndJobID(Integer id,
			Integer id2) {
		return this.repository.findByDepartmentIdAndJobTitleId(id, id2);
	}

	public List<Employee> findEmployeeByFirstName(String firstName) {
		return this.repository.findByFirstName(firstName);
	}

	public List<Employee> findEmployeeByName(String firstName, String lastName) {
		return this.repository.findByFirstNameOrLastName(firstName, lastName);
	}

	public List<Employee> findEmployeeByFullName(String firstName, String lastName) {
		return this.repository.findByFirstNameAndLastName(firstName, lastName);
	}

	public List<Employee> findEmployeeAutoByName(String firstName, String lastName) {
		return this.repository.findByFirstNameStartingWithOrLastNameStartingWith(firstName, lastName);
	}

	public List<Employee> findEmployeeAutoByFullName(String firstName,
			String lastName) {
		return this.repository.findByFirstNameAndLastNameStartingWith(firstName, lastName);
	}
}
