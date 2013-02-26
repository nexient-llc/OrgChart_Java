package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Department;

public interface DepartmentDAO {

	//create a new department record, return the generated ID
	public abstract Integer save(Department department);
	
	//update an existing department record
	public abstract void update(Department department);
	
	//delete a selected record
	public abstract void delete(Department department);

	//find all departments
	public abstract List<Department> findAll();

	//find a specific department by ID
	public abstract Department findById(Integer id);

	//find a list of departments by whole or partial name
	public abstract List<Department> findByName(String name);

	//find a list of departments based on parent department
	public abstract List<Department> findByParentDepartment(Department department);

}