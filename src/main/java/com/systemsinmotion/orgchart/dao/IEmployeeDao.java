package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Employee;

public interface IEmployeeDao {
	
	public abstract Integer save(Employee employee);
	
	public abstract void delete(Employee employee);
	
	public abstract List<Employee> findAll();
	
	public abstract Employee findById(Integer id);
	
	public abstract void update(Employee employee);
	
	public abstract List<Employee> findByDept(Integer deptId);
	
	public abstract List<Employee> findByManager(Boolean isManager);
	
	public abstract List<Employee> findByManagerId(Integer managerId);
	
	public abstract Employee findByEmail(String email);

}
