package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.Employee;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("lookupService")
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	public List<Employee> findAll() {
		return this.repository.findAll();
	}

	public Employee findOne(Integer id) {
		return this.repository.findOne(id);
	}

	public Integer saveOrUpdate(Employee employee) {
		return this.repository.save(employee).getId();
	}

	public void deleteEmployee(Employee employee) {
		this.repository.delete(employee);
	}
}
