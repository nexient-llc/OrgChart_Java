package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Employee;

public interface EmployeeDao {

	public abstract Integer save(Employee employee);

	public abstract List<Employee> findAll();

}