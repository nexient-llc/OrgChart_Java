package com.systemsinmotion.orgchart.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.systemsinmotion.orgchart.dao.IEmployeeDao;
import com.systemsinmotion.orgchart.entity.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	IEmployeeDao EmployeeDao;
	
	public void setEmployeeDao(IEmployeeDao employeeDao){
		this.EmployeeDao = employeeDao;
	}
	
	public Integer storeEmployee(Employee employee){
		return this.EmployeeDao.save(employee);
	}
	
	public void removeEmployee(Employee employee){
		this.EmployeeDao.delete(employee);
	}
	
	public List<Employee> findAllEmployees(){
		return this.EmployeeDao.findAll();
	}
	
	public Employee findEmployeeById(Integer employeeId){
		return this.EmployeeDao.findById(employeeId);
	}
}
