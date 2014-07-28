package com.systemsinmotion.orgchart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mysema.query.types.expr.BooleanExpression;
import com.systemsinmotion.orgchart.data.EmployeeRepository;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.QEmployee;

@SuppressWarnings("restriction")
@Service("employeeService")
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> findAllEmployees() {
		// TODO Auto-generated method stub
		return this.employeeRepository.findByIsActiveIsTrue();
	}

	public List<Employee> findByJobTitleName(String title) {
		return this.employeeRepository.findByJobTitleName(title);
	}

	public Employee findEmployeeByID(Integer employeeId) {
		// TODO Auto-generated method stub
		return this.employeeRepository.findOne(employeeId);
	}

	public Employee editEmployee(Employee employee) {

		Employee current = this.employeeRepository.findOne(employee.getId());
		current.setAll(employee);
		return this.employeeRepository.save(current);
	}

	public Employee storeEmployee(Employee employee) {

		QEmployee qEmployee = QEmployee.employee;
		Employee tempEmloyee;
		BooleanExpression predicate = qEmployee.email.eq(employee.getEmail())
				.or(qEmployee.skypeName.eq(employee.getSkypeName()));
		// see if the email and skype was unique
		List<Employee> employees = (List<Employee>) employeeRepository
				.findAll(predicate);
		// if not setup to return null
		if (employees != null && employees.size() > 0) {
			tempEmloyee = null;
		} else {
			// if it was store the employee and then return it.
			employee.setIsActive(true);
			tempEmloyee = this.employeeRepository.save(employee);
		}
		return tempEmloyee;
	}

	public Employee updateEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	public List<Employee> activeEmployees() {
		return this.employeeRepository.findByIsActiveIsTrue();
	}

	public List<Employee> filterEmployees(String firstName, String lastName,
			String department, String title) {
		QEmployee employee = QEmployee.employee;

		BooleanExpression expression = employee.isActive.eq(true);

		if (!firstName.equals(" ")) {
			BooleanExpression firstNameExpression = null;
			if (lastName.equals(" ")) {
				firstNameExpression = (employee.firstName.eq(firstName))
						.or(employee.lastName.eq(firstName));
			} else {
				firstNameExpression = (employee.firstName.eq(firstName));
			}
			expression = expression.and(firstNameExpression);
		}
		if (!lastName.equals(" ")) {
			BooleanExpression lastNameExpression = employee.lastName
					.eq(lastName);
			expression = expression.and(lastNameExpression);
		}

		if (!department.equals("")) {
			BooleanExpression departmentExpression = employee.department.id
					.eq(Integer.parseInt(department));
			expression = expression.and(departmentExpression);
		}

		if (!title.equals("")) {
			BooleanExpression jobTitleExpression = employee.jobTitle.id
					.eq(Integer.parseInt(title));
			expression = expression.and(jobTitleExpression);
		}

		List<Employee> employees = (ArrayList<Employee>) employeeRepository
				.findAll(expression);

		return employees;

	}

	public String autoComplete(String name) {
		QEmployee employee = QEmployee.employee;
		BooleanExpression expression = employee.firstName
				.containsIgnoreCase(name)
				.or(employee.lastName.containsIgnoreCase(name))
				.and(employee.isActive.eq(true));

		Gson json = new Gson();
		List<Employee> employees = (ArrayList<Employee>) employeeRepository
				.findAll(expression);

		return json.toJson(employees);

	}

}
