package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

	Department findByName(String name);

	List<Department> findByParentDepartmentId(Integer id);
	
	List<Department> findAllByIsActiveIsTrue();
	
	@Modifying
	@Transactional
	@Query("update Department dept set dept.isActive = false where dept.id = :id")
	void removeDepartment(@Param("id") Integer id);

}
