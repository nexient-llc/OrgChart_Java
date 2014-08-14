package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.entity.Department;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	public List<Department> findAllDepartments() {
		return this.departmentRepository.findAll();
	}

	public Department findDepartmentByID(Integer departmentId) {
		return this.departmentRepository.findOne(departmentId);
	}

	public List<Department> activeDepartments() {
		return this.departmentRepository.findByIsActiveIsTrue();
	}

	public Department removeDepartment(Department department) {

		Department departmentToRemove = findDepartmentByID(department.getId());
		departmentToRemove.setIsActive(false);
		return this.departmentRepository.saveAndFlush(departmentToRemove);

	}

	public Department update(Department department) {
		Department depart = findDepartmentByID(department.getId());
		depart.setName(department.getName());

		return this.departmentRepository.saveAndFlush(depart);
	}

	public void setRepository(DepartmentRepository repository) {
		this.departmentRepository = repository;
	}

	public Department storeDepartment(Department department, Object active) {

		if (department.getParentDepartment() != null
				&& department.getParentDepartment().getId() == null) {
			department.setParentDepartment(null);
		}
		if (departmentRepository.findByName(department.getName()) != null) {
			department = departmentRepository.findByName(department.getName());
		}
		department.setIsActive(((active == null) ? true : false));

		return this.departmentRepository.save(department);
	}

}
