package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.entity.Department;

@Service("departmentService")
@Transactional(readOnly = true)
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
	
	public List<Department> findDepartmentByName(String name)
	{
		return this.repository.findByNameIgnoreCase(name);
	}

	@Secured("ROLE_ADMIN")
	@Transactional
	public void removeDepartment(Department department) {
		department.setIsActive(false);
		this.repository.save(department);
//		this.repository.delete(department);
	}

	@Transactional
	public void removeDepartmentById(Integer deptId) {
		Department department = this.repository.findById(deptId);
		removeDepartment(department);
	}

	public void setRepository(DepartmentRepository repository) {
		this.repository = repository;
	}

	public DepartmentRepository getRepository() {
		return this.repository;
	}

	@Secured("ROLE_ADMIN")
	@Transactional
	public Department storeDepartment(Department department) {
		if (department.getParentDepartment() != null && department.getParentDepartment().getId() == null)
		{
			department.setParentDepartment(null);
		}
		if (department.getIsActive() == null)
		{
			department.setIsActive(true);
		}
		return this.repository.save(department);
	}
}
