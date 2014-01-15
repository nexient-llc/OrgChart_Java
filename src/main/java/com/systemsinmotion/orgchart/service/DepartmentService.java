package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.entity.Department;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentRepository repository;

	public List<Department> findAllDepartments() {
		Sort sort = new Sort(Direction.ASC, "name");
		return this.repository.findAll(sort);
	}

	public Department findDepartmentByID(Integer departmentId) {
		return this.repository.findOne(departmentId);
	}

	public void removeDepartment(Department department) {
		this.repository.delete(department);
	}

	public void setRepository(DepartmentRepository repository) {
		this.repository = repository;
	}

	public Department storeDepartment(Department department) {
		return this.repository.save(department);
	}

}
