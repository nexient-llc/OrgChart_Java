package com.systemsinmotion.orgchart.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Department;

@Repository("departmentRepo")
public interface DepartmentRepository extends JpaRepository<Department,Integer> {

	Department findByName(String name);

	List<Department> findByParentDepartmentId(Integer id);

	Department findById(Integer id);

	List<Department> findByIsActiveTrue();
}
