package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsinmotion.orgchart.dao.IEmployeeDao;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;

	public Employee findById(Integer employeeId) {
		return this.employeeDao.findById(employeeId);
	}

	public void setDepartmentDAO(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public List<Employee> findAllEmployees() {
		return this.employeeDao.findAll();
	}

	public void updateEmployee(Employee employee) {
		/*
		 * An employee cannot manage himself, also handle when the employee is
		 * updated to have no manager.
		 */
		if (employee.getManager() != null) {
			if (employee.getManager().getId() == null) {
				employee.setManager(null);
			} else {
				if (employee.getManager().getId() == employee.getId())
					return;
			}
		}

		this.employeeDao.update(employee);
	}

	public Integer storeEmployee(Employee employee) {
		return this.employeeDao.save(employee);
	}

	public void removeEmployee(Employee employee) {
		this.employeeDao.delete(employee);
	}

	/*
	 * Fails if no employee by that id exists, or if the confirm string is not
	 * equivalent to their last name.
	 */
	public void removeEmployee(Integer id, String confirmString) {
		if (confirmString == null || confirmString.isEmpty())
			return;

		Employee employee = this.findById(id);

		if (employee == null)
			return;

		if (employee.getLastName().equals(confirmString))
			this.employeeDao.delete(employee);
	}

}
