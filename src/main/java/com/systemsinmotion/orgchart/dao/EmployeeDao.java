package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public interface EmployeeDao {

	public abstract Integer save(Employee employee);

	public abstract List<Employee> findAll();

	public abstract List<Employee> findByDepartment(Department department);

	public abstract Employee findByEmail(String email);

	public abstract Employee findById(Integer id);

	public abstract void update(Employee employee);

	public abstract List<Employee> findByManager(Employee manager);

}
