package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Employee;

public interface IEmployeeDao {
	
	public abstract Integer save(Employee employee);
	
	public abstract void update(Employee employee);
	
	public abstract void delete(Employee employee);
	
	public abstract List<Employee> findAll();

}
