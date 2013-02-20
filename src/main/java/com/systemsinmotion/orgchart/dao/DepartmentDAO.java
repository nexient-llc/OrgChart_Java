package com.systemsinmotion.orgchart.dao;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Department;

public interface DepartmentDAO {

	public abstract void delete(Department department);

	public abstract List<Department> findAll();

	public abstract Department findById(Integer id);

	public abstract List<Department> findByName(String name);

	public abstract List<Department> findByParentDepartment(Department department);

	public abstract Integer save(Department department);

	public abstract void update(Department department);

	//public abstract String toString();

}