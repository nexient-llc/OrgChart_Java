package com.systemsinmotion.orgchart.service;

import java.util.List;
import java.util.Set;

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

	public Department findDepartmentByID(Integer departmentId) {
		return this.repository.findById(departmentId);
	}
	
	public Department findDepartmentByName(String departmentName){
		return this.repository.findByName(departmentName);
	}

	public void removeDepartment(Department department) {
		if(department != null){
			List<Department> depts = findAllDepartments();
			for(Department child : depts){
				Department parent = child.getParentDepartment();
				if(parent != null)
					if(parent.getId().equals(department.getId())){
						child.setParentDepartment(department.getParentDepartment());
						storeDepartment(child);
					}
			}
			department.setParentDepartment(null);
			storeDepartment(department);
			this.repository.delete(department);
		}
	}

	public void setRepository(DepartmentRepository repository) {
		this.repository = repository;
	}

	public Department storeDepartment(Department department) {
		//Inserting new Department with existing name
		if(findDepartmentByName(department.getName()) != null && department.getId() == null)
			return null;
		Department parent = department.getParentDepartment();
		if(parent != null)
			department.setParentDepartment(parent.getId() == null ? null : parent);
		return this.repository.save(department);
	}
}
