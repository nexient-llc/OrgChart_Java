package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.EmployeeDao;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	public Employee findEmployeeById(Integer id) {
		return this.employeeDao.findById(id);
	}

	public List<Employee> findAllEmployees() {
		return this.employeeDao.findAll();
	}

	public Integer addEmployee(Employee employee) {
		return this.employeeDao.save(employee);
	}

	public void deleteEmployee(Employee employee) {
		this.employeeDao.delete(employee);
	}
}
