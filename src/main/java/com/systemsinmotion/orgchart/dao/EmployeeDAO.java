package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

public interface EmployeeDAO {

	public Integer save(Employee employee);

	public void delete(Employee employee);

	public Employee findById(Integer notPresentId);

	public List<Employee> findAll();

	public Employee findByEmail(String notPresentValue);

	public List<Employee> findByDepartment(Department department);

	public List<Employee> findByManager(Employee employee);

	public void update(Employee employee);

}