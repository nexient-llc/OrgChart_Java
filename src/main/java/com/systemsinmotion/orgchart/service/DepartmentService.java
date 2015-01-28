package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

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
		this.repository.delete(department);
	}

	public void setRepository(DepartmentRepository repository) {
		this.repository = repository;
	}

	public Department storeDepartment(Department department) {
		return this.repository.save(department);
	}

	public Department findDepartmentByManager(Employee manager) {
		// TODO Auto-generated method stub
		return null;
	}

}
