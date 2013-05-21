package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.IEmployeeDAO;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {
	@Autowired
	IEmployeeDAO employeDAO;

	public Employee findEmployeeByID(Integer employeeId) {

		return this.employeDAO.findById(employeeId);
	}

	public void setEmployeeDAO(IEmployeeDAO employeeDAO) {
		this.employeDAO = employeeDAO;
	}

	public List<Employee> findAllEmployees() {
		return this.employeDAO.findAll();

	}

	public Integer storeEmployee(Employee employee) {
		return this.employeDAO.save(employee);
	}

	public void removeEmployee(Employee employee) {
		this.employeDAO.delete(employee);
	}
}
