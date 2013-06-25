package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.IDepartmentDao;
import com.systemsinmotion.orgchart.entity.Department;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	IDepartmentDao DepartmentDao;

	public Department findDepartmentByID(Integer departmentId) {

		return this.DepartmentDao.findById(departmentId);
	}

	public void setDepartmentDao(IDepartmentDao deparmentDao) {
		this.DepartmentDao = deparmentDao;
	}

	public List<Department> findAllDepartments() {
		return this.DepartmentDao.findAll();
	}

	public Integer storeDepartment(Department department) {
		return this.DepartmentDao.save(department);
	}

	public void removeDepartment(Department department) {
		this.DepartmentDao.delete(department);
	}
	
	//
	// NEED TO WRITE TESTS FOR METHODS BELOW
	//
	
	public List<Department> findByParentDepartment(Department department){
		if(department.getId() == department.getParentDepartment().getId()){
			return null;
		}
		return this.DepartmentDao.findByParentDepartment(department);
	}
	
	public Department findByDepartmentName(Department department){
		
		return this.DepartmentDao.findByName(department.getName());
	}
	
	public void updateDepartment(Department department){
		this.DepartmentDao.update(department);
	}
}
