package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.entity.Department;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentRepository repository;

	public List<Department> findAllDepartments() {
		return this.repository.findAll();
	}
	
	public List<Department> findAllActiveDepartments() {
		return this.repository.findByIsActiveIsTrue();
	}

	public Department findDepartmentByID(Integer departmentId) {
		return this.repository.findOne(departmentId);
	}
	@Transactional
	public void removeDepartment(Department dep) {
		this.repository.delete(dep);
	}
	
	public long rowCount(){
		return this.repository.count();
	}

	public void setRepository(DepartmentRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public Department storeDepartment(Department department) {
		return this.repository.saveAndFlush(department);
	}
	
	public Department findByName(String name){
		return this.repository.findByName(name);
	}
	
	public List<Department> findDeparmentsWithString(String str){
		return this.repository.findByNameContaining(str);
	}

	public Page<Department> getPage(Integer page, Integer size) {
		return this.repository.findAll(new PageRequest(page, size));
	}
}
