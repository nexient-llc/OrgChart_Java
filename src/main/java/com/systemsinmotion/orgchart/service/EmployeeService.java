package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.EmployeeDAO;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("lookupService")
public class EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;

	public List<Employee> findAllEmployees() {
		return this.employeeDAO.findAll();
	}

	public Employee findEmployeeByID(int IdNum) {
		return this.employeeDAO.findById(IdNum);
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public Integer storeEmployee(Employee employee) {
		return this.employeeDAO.save(employee);
	}
	
	public void updateEmployee(Employee employee){
	    this.employeeDAO.update(employee);
	}
	
	public void deleteEmployee(Employee employee){
	    this.employeeDAO.delete(employee);
	}

}
