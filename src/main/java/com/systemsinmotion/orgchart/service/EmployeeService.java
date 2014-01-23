package com.systemsinmotion.orgchart.service;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeService {

	@Autowired
    EmployeeRepository repository;

	public List<Employee> findAllEmployees() {
		return this.repository.findAll();
	}

	public Employee findEmployeeByID(Integer employeeId) {
		return this.repository.findOne(employeeId);
	}

	public void removeEmployee(Employee employee) {
		this.repository.delete(employee);
	}

	public void setRepository(EmployeeRepository repository) {
		this.repository = repository;
	}

	public Employee storeDepartment(Employee employee) {
		return this.repository.save(employee);
	}

}
