package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.IEmployeeDao;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Service("employeeService")
public class EmployeeService {
	
	@Autowired
	private IEmployeeDao employeeDao;
	
	public void setEmployeeDao(IEmployeeDao mockEmployeeDao) {
		this.employeeDao = mockEmployeeDao;
	}

	public List<Employee> findAllEmployees() {
		return this.employeeDao.findAll();
	}

	public Employee findEmployeeById(Integer jobTitleId) {
		return this.employeeDao.findById(jobTitleId);
	}

	public List<Employee> findEmployeesByDepartment(Department department) {
		return this.employeeDao.findByDepartment(department);
	}

	public List<Employee> findEmployeesByJobTitle(JobTitle jobTitle) {
		return this.employeeDao.findByJobTitle(jobTitle);
	}

	public Employee findEmployeeByEmail(String email) {
		return this.employeeDao.findByEmail(email);
	}

	public Integer storeEmployee(Employee employee) {
		return this.employeeDao.save(employee);
	}

	public void updateEmployee(Employee employee) {
		this.employeeDao.update(employee);
	}

	public void removeEmployee(Employee employee) {
		this.employeeDao.delete(employee);
	}
}
