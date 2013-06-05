package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.DepartmentDao;
import com.systemsinmotion.orgchart.entity.Department;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentDao departmentDAO;

	public Department findDepartmentByID(Integer departmentId) {

		return this.departmentDAO.findById(departmentId);
	}

	public void setDepartmentDAO(DepartmentDao deparmentDAO) {
		this.departmentDAO = deparmentDAO;
	}

	public List<Department> findAllDepartments() {
		return this.departmentDAO.findAll();
	}

	public Integer storeDepartment(Department department) {
		dontChangeParentIfSameAsDept(department);
		setParentNullIfNoParent(department);
		return this.departmentDAO.save(department);
	}

	public void removeDepartment(Department department) {
		this.departmentDAO.delete(department);
	}

	public void updateDepartment(Department department) {
		dontChangeParentIfSameAsDept(department);
		setParentNullIfNoParent(department);
		this.departmentDAO.update(department);
	}

	private void setParentNullIfNoParent(Department department) {
		Department parentDepartment = department.getParentDepartment();
		if (parentDepartment != null
				&& parentDepartment.getDepartmentId() == null) {
			department.setParentDepartment(null);
		}
	}

	private void dontChangeParentIfSameAsDept(Department department) {
		if ((department.getDepartmentId() != null && department
				.getParentDepartment().getDepartmentId() != null)
				&& (department.getDepartmentId().intValue() == department
						.getParentDepartment().getDepartmentId().intValue())) {
			Department currentDeptInDb = departmentDAO.findById(department
					.getDepartmentId());
			department.setParentDepartment(currentDeptInDb
					.getParentDepartment());

		}
	}

}
