package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.DepartmentDao;
import com.systemsinmotion.orgchart.entity.Department;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentDao DepartmentDao;

	public Department findDepartmentByID(Integer departmentId) {

		return this.DepartmentDao.findById(departmentId);
	}

	public void setDepartmentDAO(DepartmentDao deparmentDAO) {
		this.DepartmentDao = deparmentDAO;
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

}
