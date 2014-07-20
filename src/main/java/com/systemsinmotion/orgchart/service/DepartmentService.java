package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.entity.Department;

@Service("DepartmentService")
public class DepartmentService {

	private static final Sort defaultSort = new Sort(Direction.ASC, "name");

	@Autowired
	DepartmentRepository repository;

	public List<Department> findAllDepartments() {
		return this.repository.findAll();
	}

	public Department findDepartmentByID(Integer departmentId) {
		return this.repository.findOne(departmentId);
	}

	public List<Department> activeDepartments() {
		return this.repository.findByIsActiveIsTrue();
	}

	public Department removeDepartment(Department department) {
		Department departmentToRemove = findDepartmentByID(department.getId());
		departmentToRemove.setIsActive(false);

		return this.repository.saveAndFlush(departmentToRemove);
	}

	public Department update(Department department) {
		Department depart = findDepartmentByID(department.getId());
		depart.setName(department.getName());

		return this.repository.saveAndFlush(depart);
	}

	public void setRepository(DepartmentRepository repository) {
		this.repository = repository;
	}

	public Department storeDepartment(Department department) {

		if (department.getParentDepartment() != null
				&& department.getParentDepartment().getId() == null) {
			department.setParentDepartment(null);
		}
		if (repository.findByName(department.getName()) != null) {
			department = repository.findByName(department.getName());
		}
		department.setIsActive(true);
		return this.repository.save(department);
	}

}
