package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentRepository repository;
	
	@Autowired
	EmployeeService employeeService;

	public List<Department> findAllDepartments() {
		return this.repository.findAll();
	}

	public List<Department> findAllActiveDepartments() {
		return this.repository.findByIsActiveTrue();
	}
	
	public Department findDepartmentById(Integer departmentId) {
		return this.repository.findOne(departmentId);
	}

	public Department findDepartmentByName(String name){
		return this.repository.findByName(name);
	}
	
	public void removeDepartment(Department department) {
		if(department != null){
			List<Department> depts = repository.findAll();
			for(Department child : depts){
				Department parent = child.getParentDepartment();
				if(parent != null){
					if(parent.getId().equals(department.getId())){
						child.setParentDepartment(null);
						storeDepartment(child);
					}
				}
			}
			List<Employee> emps = employeeService.findEmployeesByDepartment(department);
			for(Employee emp : emps){
				emp.setDepartment(null);
				employeeService.storeEmployee(emp);
			}
			department.setIsActive(false);
		}
		this.repository.save(department);
	}

	public void setRepository(DepartmentRepository repository) {
		this.repository = repository;
	}

	public Department storeDepartment(Department department) {
		return this.repository.save(department);
	}

}
