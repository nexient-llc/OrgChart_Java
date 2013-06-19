package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.systemsinmotion.orgchart.dao.EmployeeDao;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeDao employeeDAO;

	public Employee findEmployeeByID(Integer employeeId) {
		return this.employeeDAO.findById(employeeId);
	}

	public void setEmployeeDAO(EmployeeDao employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public List<Employee> findAllEmployees() {
		return this.employeeDAO.findAll();
	}

	public Integer storeEmployee(Employee employee) {
		return this.employeeDAO.save(employee);
	}

	public void removeEmployee(Employee employee) {
		this.employeeDAO.delete(employee);
	}

}
