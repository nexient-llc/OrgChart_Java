package com.systemsinmotion.orgchart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mysema.query.types.expr.BooleanExpression;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.QEmployee;

@Service("employeeService")
public class EmployeeService {
	@Autowired
	EmployeeRepository repository;

	public List<Employee> findAllEmployees() {
		return this.repository.findAll();
	}

	public List<Employee> findAllActiveEmployees() {
		return this.repository.findByIsActiveIsTrue();
	}

	public Employee findEmployeeByID(Integer employeeId) {
		return this.repository.findOne(employeeId);
	}

	public Employee storeEmployee(Employee employee) {
		return this.repository.save(employee);
	}

	public Employee saveEmployee(Employee employee) {
		employee.setIsActive(true);
		return storeEmployee(employee);
	}
	
	public void removeEmployee(Employee employee) {
		employee.setIsActive(false);
		storeEmployee(employee);
	}

	public List<Employee> findByCriteria(Employee emp) {
		QEmployee employee = QEmployee.employee;

		BooleanExpression exp = employee.isActive.eq(true);

		if (StringUtils.hasText(emp.getFirstName())) {
			exp = exp.and(employee.firstName.eq(emp.getFirstName()));
		}

		if (emp.getLastName() != null && emp.getLastName().length() > 0) {
			exp = exp.and(employee.lastName.eq(emp.getLastName()));
		}

		if (emp.getDepartment() != null && emp.getDepartment().getId() != null) {
			exp = exp.and(employee.department.id
					.eq(emp.getDepartment().getId()));
		}

		if (emp.getJobTitle() != null && emp.getJobTitle().getId() != null) {
			exp = exp.and(employee.jobTitle.id.eq(emp.getJobTitle().getId()));
		}

		List<Employee> emps = (ArrayList<Employee>) repository.findAll(exp);

		return emps;

	}

}
