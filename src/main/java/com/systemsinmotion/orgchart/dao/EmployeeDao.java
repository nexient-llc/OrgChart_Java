package com.systemsinmotion.orgchart.dao;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@Repository
public class EmployeeDao implements IEmployeeDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(EmployeeDao.class);

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public Integer save(Employee employee) {
		LOG.debug("Saving Employee with name: " + employee.getFirstName() + " "
				+ employee.getLastName());
		try {
			return (Integer) this.hibernateTemplate.save(employee);
		} catch (RuntimeException re) {
			LOG.error("save failed: ", re);
			throw re;
		}
	}

	@Override
	public void delete(Employee employee) {
		LOG.debug("Deleting employee with name: " + employee.getFirstName()
				+ " " + employee.getLastName());
		try {
			this.hibernateTemplate.delete(employee);
		} catch (RuntimeException re) {
			LOG.error("Delete failed: ", re);
			throw re;
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		LOG.debug("Finding all employees");
		try {
			return this.hibernateTemplate.find(" from "
					+ Employee.class.getName() + " order by last_name");
		} catch (RuntimeException re) {
			LOG.error("finding all failed: ", re);
			throw re;
		}
	}

	@Override
	public Employee findById(Integer id) {
		LOG.debug("Finding employee by id: " + id);
		try {
			return this.hibernateTemplate.get(Employee.class, id);
		} catch (IllegalArgumentException re) {
			return null;
		} catch (RuntimeException re) {
			LOG.error("Finding by id failed; ", re);
			throw re;
		}
	}

	@Override
	public void update(Employee employee) {
		LOG.debug("Updating employee: " + employee.getFirstName() + " "
				+ employee.getLastName());
		try {
			this.hibernateTemplate.update(employee);
		} catch (RuntimeException re) {
			LOG.error("updating employee failed: ", re);
			throw re;
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByDept(Department department) {
		List<Employee> emps = Collections.EMPTY_LIST;
		if (department != null && department.getDepartmentId() != null) {
			LOG.debug("find employee by department: " + department.getName());
			emps = (List<Employee>) this.hibernateTemplate
					.find(" from Employee where department.departmentId = "
							+ department.getDepartmentId());
		}
		return emps;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByIsManager(Boolean isManager) {
		LOG.debug("Finding employee by manager: " + isManager);
		try {
			return this.hibernateTemplate
					.find(" from Employee where isManager = " + isManager);
		} catch (RuntimeException re) {
			LOG.error("Finding employee by manager failed: ", re);
			throw re;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Employee findByEmail(String email) {
		LOG.debug("Finding employee by email: " + email);
		try {
			Employee emp = null;
			List<Employee> emps = this.hibernateTemplate.find(" from "
					+ Employee.class.getName() + " where EMAIL =?", email);
			if (emps.size() > 0) {
				emp = emps.get(0);
			}
			return emp;
		} catch (RuntimeException re) {
			LOG.error("Finding by email failed: ", re);
			throw re;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByManagerId(Integer managerId) {
		LOG.debug("Finding by manager id: " + managerId);
		try {
			return (List<Employee>) this.hibernateTemplate.get(Employee.class,
					managerId);
		} catch (IllegalArgumentException re) {
			return null;
		} catch (RuntimeException re) {
			LOG.error("Finding by manager id failed: ", re);
			throw re;
		}
	}
}
