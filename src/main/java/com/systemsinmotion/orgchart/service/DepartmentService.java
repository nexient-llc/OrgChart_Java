package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.IDepartmentDao;
import com.systemsinmotion.orgchart.entity.Department;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	IDepartmentDao departmentDAO;

	public Department findDepartmentByID(Integer departmentId) {
		return this.departmentDAO.findById(departmentId);
	}

	public void setDepartmentDao(IDepartmentDao mockDepartmentDao) {
		this.departmentDAO = mockDepartmentDao;
	}

	public List<Department> findAllDepartments() {
		return this.departmentDAO.findAll();
	}

	public Integer storeDepartment(Department department) {
		validateDepartment(department);
		return this.departmentDAO.save(department);
	}

	public void removeDepartment(Department department) {
		this.departmentDAO.delete(department);
	}
	
	private void validateDepartment(Department department) {
		Department parentDepartment = this.findDepartmentByID(department.getParentDepartment().getId());
		
		//Make sure the department is not its own parent
		if (parentDepartment != null && parentDepartment.getId() == department.getId()) {
			parentDepartment = null;
		}
		
		department.setParentDepartment(parentDepartment);
	}

}
