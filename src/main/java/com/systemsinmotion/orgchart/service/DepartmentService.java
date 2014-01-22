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

	public Department findDepartmentByID(Integer departmentId) {
		return this.repository.findById(departmentId);
	}
	
	public Department findDepartmentByName(String name){
		return this.repository.findByName(name);
	}

	public void removeDepartment(Department department) {
		changeChildDepartmentsParent(department);
		department.setParentDepartment(null);
		storeDepartment(department);
		this.repository.delete(department);
	}

	public void setRepository(DepartmentRepository repository) {
		this.repository = repository;
	}
	
	public List<Department> findAllActiveDepartments(){
		return this.repository.findByIsActiveTrue();
	}

	public Department storeDepartment(Department department) {
		department.setIsActive(true);
		if(findDepartmentByName(department.getName()) != null && department.getId() == null) {
			return null;
		}
		Department parent = department.getParentDepartment();
		if( parent != null){
			department.setParentDepartment(parent.getId() == null ? null : parent);
		}
		return this.repository.saveAndFlush(department);
	}
	
	public Department storeInactiveDepartment(Department department) {
		department.setIsActive(false);
		if(findDepartmentByName(department.getName()) != null && department.getId() == null) {
			return null;
		}
		Department parent = department.getParentDepartment();
		if( parent != null){
			department.setParentDepartment(parent.getId() == null ? null : parent);
		}
		return this.repository.saveAndFlush(department);
	}

	public void makeDepartmentInactive(Department department) {
		changeChildDepartmentsParent(department);
		storeInactiveDepartment(department);
	}
	
	private void changeChildDepartmentsParent( Department changingParent) {
		List<Department> allDepts = this.repository.findAll();
		for(Department possibleChild : allDepts){
			if(possibleChild.getParentDepartment() == null){
				//DO NOTHING
			}
			else if(possibleChild.getParentDepartment().getId().equals(changingParent.getId())) {
				if(changingParent.getParentDepartment() == null){
					possibleChild.setParentDepartment(null);
				}
				else {
					possibleChild.setParentDepartment(changingParent.getParentDepartment());
				}
				storeDepartment(possibleChild);
			}
		}
	}

}
