package com.systemsinmotion.orgchart.data;

import java.util.List;

import com.systemsinmotion.orgchart.entity.Department;

public interface DepartmentRepository extends BaseRepository<Department,Integer> {

	Department findByName(String name);

	List<Department> findByParentDepartmentId(Integer id);
	
	List<Department> findByNameContaining(String str);
}
