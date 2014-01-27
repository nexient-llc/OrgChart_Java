package com.systemsinmotion.orgchart.service;

import com.systemsinmotion.orgchart.data.DepartmentRepository;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeService {

	@Autowired
    EmployeeRepository repository;

	public List<Employee> findAllEmployees() {
		return this.repository.findAll();
	}

	public Employee findById(Integer employeeId) {
		return this.repository.findById(employeeId);
	}

    public Employee findEmployeeByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    public List<Employee> findByName(String first, String last) {
        return this.repository.findByFirstNameOrLastNameAllIgnoreCase(first, last);
    }

	public void removeEmployee(Employee employee) {
		this.repository.delete(employee);
	}


	public void setRepository(EmployeeRepository repository) {
		this.repository = repository;
	}

	public Employee storeEmployee(Employee employee) {
		return this.repository.save(employee);
	}

}
