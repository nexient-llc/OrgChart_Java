package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.entity.Department;

@Service("DepartmentService")
public class DepartmentService {

	private static final Sort defaultSort = new Sort(Direction.ASC, "name");
	
	@Autowired
	DepartmentRepository repository;

	public List<Department> findAllDepartments() {
		return this.repository.findAll();
	}

	public Department findDepartmentByID(Integer departmentId) {
		return this.repository.findOne(departmentId);
	}
    
	public List<Department> activeDepartments(){
		return this.repository.findByIsActiveIsTrue();
	}
	public void removeDepartment(Department department) {
		Department departmentToRemove = findDepartmentByID(department.getId());
		departmentToRemove.setIsActive(false);
		
		this.repository.save(departmentToRemove);
	}

	public void setRepository(DepartmentRepository repository) {
		this.repository = repository;
	}

	public Department storeDepartment(Department department) {
		if(department.getParentDepartment() != null && department.getParentDepartment().getId() == null){
			department.setParentDepartment(null);
		}
        department.setIsActive(true);
        
	return this.repository.save(department);
	}
	
	public Department findByName(String name, String sortField, String sortDir) {
		Assert.notNull(name,  "Name is required but was null");
		Sort sort = determineSort(sortField, sortDir);
		return repository.findByName(name, sort);
	}
    
	public boolean deparmentExists(Department depart) {
		return repository.exists(depart.getId());
	}
	
	protected Sort determineSort(String sortField, String sortDir) {
		Sort sort = null;
		if(StringUtils.hasText(sortField) && StringUtils.hasText(sortDir)){
			sort = new Sort(Direction.valueOf(sortDir), sortField);
		}else{
			sort = defaultSort;
		}			
		return sort;
	}
	
	
	

}
