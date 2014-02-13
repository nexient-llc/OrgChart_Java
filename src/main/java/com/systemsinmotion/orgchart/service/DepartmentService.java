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

	public Department findDepartmentByName(String name) {
		return this.repository.findDepartmentByName(name);
	}
	
	public List<Department> findDepartmentsByIsActiveTrue() {
		return this.repository.findDepartmentsByIsActiveTrue();
	}

	public Department setInactiveDepartment(Department department) throws Exception {
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
	
	public boolean findLoop(Department department) {
		if(department == null) {
			return false;
		}
		Department departmentA = department;
		Department departmentB = department.getParentDepartment();
		
		while(departmentB != null) {
			departmentA = departmentA.getParentDepartment();
			if(departmentB.getParentDepartment() == null)
				return false;
			departmentB = departmentB.getParentDepartment().getParentDepartment();
			if(departmentA == null || departmentB == null)
				return false;
			if(departmentA.getId() == departmentB.getId()) {
				return true;
			}
		}
		return false;
	}

	public void setRepository(DepartmentRepository repository) {
		this.repository = repository;
	}

	public Department storeDepartment(Department department) throws Exception {
		Department tempDepartment = this.repository.findOne(department.getId());
		this.repository.save(department);
		if(findLoop(findDepartmentById(department.getId()))) {
			this.repository.save(tempDepartment);
			throw new Exception("Nested loop found!");
		}
		return this.repository.findOne(department.getId());
	}
	
	public Department storeNewDepartment(Department department) {
		return this.repository.save(department);
	}
}
