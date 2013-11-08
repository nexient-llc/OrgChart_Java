package com.systemsinmotion.orgchart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.systemsinmotion.orgchart.dao.IEmployeeDao;
import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;

	public Employee findById(Integer employeeId) {
		return this.employeeDao.findById(employeeId);
	}

	public void setEmployeeDao(IEmployeeDao employeeDao) {
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

		try {
			this.employeeDao.update(employee);
		} catch (Exception e) {
		}
	}

	public Integer storeEmployee(Employee employee) {
		/* If manager is left blank null the reference. */
		if (employee.getManager() != null) {
			if (employee.getManager().getId() == null) {
				employee.setManager(null);
			}
		}

		try {
			return this.employeeDao.save(employee);
		} catch (Exception e) {
		}

		return null;
	}

	public void removeEmployee(Employee employee) {
		/*
		 * Silently fail, this usually happens when you're trying to delete a
		 * user when other users have him as their manager.
		 */
		try {
			this.employeeDao.delete(employee);
		} catch (Exception e) {
		}

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
			removeEmployee(employee);
	}
	
	public List<Employee> findByDepartment(Department department) {
		if(null == department)
			return null;
		
		return employeeDao.findByDepartment(department);
	}
	
	public List<Employee> findByManager(Employee manager) {
		if(null == manager)
			return null;
		
		return employeeDao.findByManager(manager);
	}
	
	public Employee findByEmail(String email) {
		if(null == email)
			return null;
		
		return employeeDao.findByEmail(email);
	}

	public List<Employee> findByFullName(String name) {
		if (name == null)
			return null;

		if (!StringUtils.hasText(name))
			return null;

		String[] parts = StringUtils.split(name, " ");

		if (null == parts)
			return employeeDao.findByFullName(name, null);

		return employeeDao.findByFullName(parts[0], parts[1]);
	}
}
