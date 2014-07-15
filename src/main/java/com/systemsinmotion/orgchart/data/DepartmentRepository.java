package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.systemsinmotion.orgchart.entity.Department;


public interface DepartmentRepository extends BaseRepository<Department, Integer> {

	Department findByName(String name);
	
	Department findByNameOrderByNameAsc(String name);
	
	Department findByName(String name, Sort sort);

	List<Department> findByParentDepartmentId(Integer id);
	
	List<Department> findByParentDepartmentId(Sort sort);
	
	
	
}