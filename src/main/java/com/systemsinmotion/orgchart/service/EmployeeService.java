package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.EmployeeDao;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

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
		Department empDepartment = employee.getDepartment();
		JobTitle empJobTitle = employee.getJobTitle();
		if (empDepartment != null && empDepartment.getDepartmentId() == null) {
			employee.setDepartment(null);
		}
		if (empJobTitle != null && empJobTitle.getId() == null) {
			employee.setJobTitle(null);
		}
		return this.employeeDao.save(employee);
	}

	public void deleteEmployee(Employee employee) {
		this.employeeDao.delete(employee);
	}

	public void updateEmployee(Employee employee) {
		this.employeeDao.update(employee);
	}

	public List<Employee> findEmployeeByLastName(String lastName) {
		return this.employeeDao.findByLastName(lastName);
	}

	public List<Employee> findEmployeeByFirstName(String firstName) {
		return this.employeeDao.findByFirstName(firstName);
	}

	public List<Employee> findEmployeeByDept(Department department) {
		return this.employeeDao.findByDept(department);
	}

	public List<Employee> findEmployeeByJob(JobTitle jobTitle) {
		return this.employeeDao.findByJobTitle(jobTitle);
	}

}
