package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.DepartmentDAO;
import com.systemsinmotion.orgchart.dao.EmployeeDAO;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentDAO departmentDAO;
	
	@Autowired
	EmployeeDAO employeeDAO;

	public Department findDepartmentByID(Integer departmentId) {

		return this.departmentDAO.findById(departmentId);
	}

	public void setDepartmentDAO(DepartmentDAO deparmentDAO) {
		this.departmentDAO = deparmentDAO;
	}

	public List<Department> findAllDepartments() {
		return this.departmentDAO.findAll();

	}

	public Integer storeDepartment(Department department) {
		return this.departmentDAO.save(department);
	}

	public void removeDepartment(Department department) {
		
		//find all employees in the department that is to be deleted
		List<Employee> emps = employeeDAO.findByDepartment(department);
		
		//update each employee record to remove the soon to be invalid department id
		for (Employee emp : emps)
		{
			emp.setDept(null);
			employeeDAO.update(emp);
			
		}
		
		//now remove the department from the department table
		this.departmentDAO.delete(department);
		
	}

}
