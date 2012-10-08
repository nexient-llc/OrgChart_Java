package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@Repository("employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	HibernateTemplate hibernateTemplate;

	public Integer save(Employee employee) {
		try {
			return (Integer) this.hibernateTemplate.save(employee);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Employee employee) {
		try {
			this.hibernateTemplate.delete(employee);
		} catch (RuntimeException re) {
			throw re;
		}

	}

	public Employee findById(Integer id) {
		return this.hibernateTemplate.get(Employee.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		try {
			return this.hibernateTemplate.find("from "
					+ Employee.class.getName()
					+ " order by first_name, last_name");
		} catch (RuntimeException re) {
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	public Employee findByEmail(String email) {
		try {
			Employee emp = null;
			List<Employee> emps = this.hibernateTemplate.find(" from "
					+ Employee.class.getName() + " where EMAIL = ?", email);
			if (emps.size() > 0) {
				emp = emps.get(0);
			}
			return emp;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findByDepartment(Department department) {
		try {
			return this.hibernateTemplate.find(" from "
					+ Employee.class.getName() + " where DEPARTMENT_ID = "
					+ department.getDepartmentId());
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findByManager(Employee employee) {

		try {
			return this.hibernateTemplate.find(" from "
					+ Employee.class.getName() + " where MANAGER_ID = "
					+ employee.getEmployeeId());
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void update(Employee employee) {
		try {
			this.hibernateTemplate.update(employee);
		} catch (RuntimeException re) {
			throw re;
		}

	}

}
