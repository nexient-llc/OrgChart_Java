package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.Department;
import com.nexient.orgchart.data.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentRepository repository;

	public Department findOne(Integer departmentId) {
		return this.repository.findOne(departmentId);
	}

	public List<Department> findAll() {
		return this.repository.findAll();
	}

	public Integer saveOrUpdate(Department department) {
		return this.repository.save(department).getId();
	}

	public void removeDepartment(Department department) {
		this.repository.delete(department);
	}
}
