package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Department;

@Repository("departmentRepo")
public interface DepartmentRepo extends JpaRepository<Department, Integer>{

	public abstract List<Department> findByParentDepartment(Department department);
	public abstract Department findById(Integer id);
	public abstract Department findByName(String name);
	public abstract void update(Department department);
}
