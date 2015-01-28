package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.entity.Department;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentRepository repository;

	public List<Department> findAllDepartments() {
		return this.repository.findAll();
	}

	public List<Department> findAllActiveDepartments() {
		return this.repository.findByIsActiveIsTrue();
	}

	public Department findDepartmentByID(Integer departmentId) {
		return this.repository.findOne(departmentId);
	}

	public void removeDepartment(Department department) {
		department.setIsActive(false);
		storeDepartment(department);
	}

	public void setRepository(DepartmentRepository repository) {
		this.repository = repository;
	}

	@Transactional
	private Department storeDepartment(Department department) {
		return repository.save(department);
	}

	@Transactional
	public Department saveDepartment(Department department) {
		Department parentDepartment = department.getParentDepartment();
		if (parentDepartment != null && parentDepartment.getId() == null)
			department.setParentDepartment(null);
		department.setIsActive(true);
		return storeDepartment(department);
	}

}
