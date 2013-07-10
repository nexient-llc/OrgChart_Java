package com.systemsinmotion.orgchart.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.systemsinmotion.orgchart.dao.IDepartmentDao;
import com.systemsinmotion.orgchart.entity.Department;

// THROW ERRORS HERE
// CATCH THEM IN CONTROLLER

@Service("departmentService")
@Transactional
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
	
	public List<Integer> findAllParentDepartmentIds(){
		return this.DepartmentDao.findAllParentDepartmentIds();
	}

	public Integer storeDepartment(Department department) {
		return this.DepartmentDao.save(department);
	}

	public void removeDepartment(Department department) {
			this.DepartmentDao.delete(department);
	}
	
	public void updateDepartment(Department department){
		this.DepartmentDao.update(department);
	}
	
	public void findDepartmentByName(String deptName){
		this.DepartmentDao.findByName(deptName);
	}
	
	public void findDeptByParent(Department parent){
		this.DepartmentDao.findByParentDepartment(parent);
	}
	
}
