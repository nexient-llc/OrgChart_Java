package com.systemsinmotion.orgchart.dao;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Department;

public interface DepartmentRepoNOTUSED extends JpaRepository<Department, Integer>{
	
	List<Department> findAll();

	Department findById(Integer id);

	Department findByName(String name);

	List<Department> findByParentDepartment(Department parentDepartment);

	void update(Department dept);

}
