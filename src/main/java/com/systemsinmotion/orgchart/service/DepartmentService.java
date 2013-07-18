package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.dao.DepartmentDao;
import com.systemsinmotion.orgchart.entity.Department;
@Transactional
@Service("departmentService")
public class DepartmentService {

	@Autowired
	private DepartmentDao departmentDAO;

	public Department findDepartmentByID(Integer departmentId) {

		return this.departmentDAO.findById(departmentId);
	}
	
	public Department findDepartmentByName(String deptName) {

		return this.departmentDAO.findByName(deptName);
	}

public void updateDepartment(Department department){

	this.departmentDAO.update(department);
	
}
	
	public void setDepartmentDAO(DepartmentDao deparmentDAO) {
		this.departmentDAO = deparmentDAO;
	}

	public List<Department> findAllDepartments() 
	{
		return this.departmentDAO.findAll();

	}

	public Integer storeDepartment(Department department) {
		return this.departmentDAO.save(department);
	}

	public void removeDepartment(Department department) {
		this.departmentDAO.delete(department);
	}

}
