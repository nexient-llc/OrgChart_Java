package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.IDepartmentDao;
import com.systemsinmotion.orgchart.entity.Department;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	private IDepartmentDao departmentDAO;

	public Department findDepartmentByID(Integer departmentId) {
		return this.departmentDAO.findById(departmentId);
	}

	public void setDepartmentDAO(IDepartmentDao deparmentDAO) {
		this.departmentDAO = deparmentDAO;
	}

	public List<Department> findAllDepartments() {
		return this.departmentDAO.findAll();

	}

	public Integer storeDepartment(Department department) {
		Department parent = department.getParentDepartment();
		if (parent != null) {
			if (parent.getId() == null || parent.getId() == -1) {
				department.setParentDepartment(null);
			}
		}

		return this.departmentDAO.save(department);
	}

	public void removeDepartment(Department department) {
		this.departmentDAO.delete(department);
	}

	public void removeDepartmentConfirm(Integer removeId, String confirmString) {
		Department department = this.findDepartmentByID(removeId);

		if (department != null && confirmString.equals(department.getName())) {
			this.departmentDAO.delete(department);
		}
	}

	public void updateDepartment(Department department) {
		Department parent = department.getParentDepartment();
		if (parent != null) {
			if (parent.getId() == null || parent.getId() == -1) {
				department.setParentDepartment(null);
			}
			if (parent.getId() == department.getId()) {
				return; // You cannot be your own parent department.
			}
		}

		this.departmentDAO.update(department);
	}

}
