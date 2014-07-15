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
	private DepartmentRepository repository;

	public List<Department> findAllDepartments() {
		return this.repository.findAll();
	}

	public List<Department> findAllActiveDepartments() {
		return this.repository.findByIsActiveIsTrue();
	
	}
	
	@Transactional(readOnly = true)
	public Department findDepartmentByID(Integer departmentId) {
		return this.repository.findOne(departmentId);
	}

	public void setRepository(DepartmentRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public void removeDepartment(Department department) {
		department.setIsActive(false);
		storeDepartment(department);
	}

	@Transactional
	public Department storeDepartment(Department department) {
		return repository.save(department);
	}
	
	@Transactional
	public Department storeNewDepartment(Department department) {
		Department parentDepartment = department.getParentDepartment();
		if(parentDepartment!=null&&parentDepartment.getId()==null)
			department.setParentDepartment(null);
		department.setIsActive(true);
		return storeDepartment(department);
	}
	
}
