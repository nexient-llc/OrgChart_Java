package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.dao.IEmployeeDao;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@Service
@Transactional
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
	
	public void updateEmployee(Employee employee){
		this.EmployeeDao.update(employee);
	}
	
	public List<Employee> findAllEmployees(){
		return this.EmployeeDao.findAll();
	}
	
	public List<Employee> findByDepartment(Department department){
		return this.findByDepartment(department);
	}
	
	public Employee findEmployeeById(Integer employeeId){
		return this.EmployeeDao.findById(employeeId);
	}
	
	public Employee findEmployeeByEmail(String email){
		return this.EmployeeDao.findByEmail(email);
	}
	
	public List<Employee> findEmployeeByManager(Employee manager){
		return this.EmployeeDao.findByManager(manager);
	}

}
