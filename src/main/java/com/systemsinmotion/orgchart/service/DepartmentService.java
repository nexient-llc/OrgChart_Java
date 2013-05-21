package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.DepartmentDao;
import com.systemsinmotion.orgchart.entity.Department;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentDao departmentDao;

	public Department findDepartmentByID(Integer departmentId) {

		return this.departmentDao.findById(departmentId);
	}

	public void setDepartmentDao(DepartmentDao deparmentDao) {
		this.departmentDao = deparmentDao;
	}

	public List<Department> findAllDepartments() {
		return this.departmentDao.findAll();

	}

	public Integer storeDepartment(Department department) {
		return this.departmentDao.save(department);
	}

	public void removeDepartment(Department department) {
		this.departmentDao.delete(department);
	}

}
