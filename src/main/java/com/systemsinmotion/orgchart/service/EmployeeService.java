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

	public Employee findEmployeeByID(Integer employeeId) {
		return this.employeeDao.findById(employeeId);
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public List<Employee> findAllEmployees() {
		return this.employeeDao.findAll();
	}

	public Integer storeEmployee(Employee employee) {
		return this.employeeDao.save(employee);
	}

	public void removeEmployee(Employee employee) {
		this.employeeDao.delete(employee);
	}

	public void updateEmployee(Employee employee) {
		this.employeeDao.update(employee);
	}

}
