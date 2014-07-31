package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsinmotion.orgchart.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

	List<Department> findByNameIgnoreCase(String name);

	List<Department> findByParentDepartmentId(Integer id);

	List<Department> findByIsActiveIsTrue();

	Department findById(Integer deptId);

	Page<Department> findByIsActiveIsTrue(Pageable page);
}
