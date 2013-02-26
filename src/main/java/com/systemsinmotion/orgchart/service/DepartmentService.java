package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.DepartmentDAO;
import com.systemsinmotion.orgchart.entity.Department;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentDAO departmentDAO;

	public void setDepartmentDAO(DepartmentDAO deparmentDAO) {
		this.departmentDAO = deparmentDAO;
	}
	
	//create
	public Integer storeDepartment(Department department) {
		return this.departmentDAO.save(department);
	}
	
	//update
	public void updateDepartment(Department department)
	{
		this.departmentDAO.update(department);
	}

	//delete
	public void removeDepartment(Department department) 
	{
		
		//retrieve all sub-departments for the one being deleted
		List<Department> subDepts = this.departmentDAO.findByParentDepartment(department);
		
		//now update the sub-department records, if any were returned
		if (subDepts.size() > 0)
		{
			for (Department dept : subDepts)
			{
				//set the parent dept to null
				dept.setParentDepartment(null);
				
				//update the record with the changes
				this.departmentDAO.update(dept);
			}
		}
		
		//finally, delete the passed department
		this.departmentDAO.delete(department);
		
	}
	
	//find all
	public List<Department> findAllDepartments() {
		return this.departmentDAO.findAll();

	}

	//find by ID
	public Department findDepartmentByID(Integer departmentId) {
		return this.departmentDAO.findById(departmentId);
	}
	
	//find by name
	public List<Department> findDepartmentByName(String name)
	{
		return this.departmentDAO.findByName(name);
	}
	

	

	

}
