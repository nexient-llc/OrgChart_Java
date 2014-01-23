package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

	//Department findDepartmentByName(String name);

	List<Department> findDepartmentsByParentDepartmentId(Integer id);
	List<Department> findDepartmentsByIsActiveTrue();
	
}
