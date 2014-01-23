package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.entity.Department;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentRepository repository;

	public List<Department> findAllDepartments() {
		return this.repository.findAll();
	}

	public Department findDepartmentById(Integer departmentId) {
		if(departmentId != null)
			return this.repository.findOne(departmentId);
		else
			return null;
	}
	
	public List<Department> findDepartmentsByParentDepartmentId(Integer parentDepartmentId) {
		if(parentDepartmentId != null)
			return this.repository.findDepartmentsByParentDepartmentId(parentDepartmentId);
		else
			return null;
	}
	
	public List<Department> findDepartmentsByIsActiveTrue() {
		return this.repository.findDepartmentsByIsActiveTrue();
	}

	public Department setInactiveDepartment(Department department) {
		Department parentDepartment = findDepartmentById(department.getId()).getParentDepartment();
		List<Department> departments = findDepartmentsByParentDepartmentId(department.getId());
		
		for(Department myDepartment : departments) {
			myDepartment.setParentDepartment(parentDepartment);
			storeDepartment(myDepartment);
		}
		
		Department saveDepartment = findDepartmentById(department.getId());
		saveDepartment.setParentDepartment(null);
		saveDepartment.setIsActive(false);
		return storeDepartment(saveDepartment);
	}

	public void setRepository(DepartmentRepository repository) {
		this.repository = repository;
	}

	public Department storeDepartment(Department department) {
		return this.repository.save(department);
	}
}
