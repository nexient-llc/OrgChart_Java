package com.systemsinmotion.orgchart.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository("employeeDao")
public class EmployeeDao implements IEmployeeDao {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeDao.class);
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public void delete(Employee employee) {
		LOG.debug("deleting Employee instance with name: " + employee.getFirstName() + " " + employee.getLastName());
		this.hibernateTemplate.delete(employee);
	}

	@Override
	public List<Employee> findAll() {
		LOG.debug("finding all Employee instances");
		return this.hibernateTemplate.loadAll(Employee.class);
	}

	@Override
	public Employee findById(Integer id) {
		LOG.debug("getting Employee instance with id: " + id);
		Employee employee = null;
		
		if (id != null) {
			employee = this.hibernateTemplate.get(Employee.class, id);
		}
		
		return employee;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Employee findByEmail(String email) {
		LOG.debug("getting Employee instance with email: " + email);
		Employee employee = null;
		List<Employee> employees = null;
		
		if (email != null) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("email", email));
			
			employees = this.hibernateTemplate.findByCriteria(criteria);
		}
		
		if (employees != null && employees.size() > 0) {
			employee = employees.get(0);
		}
		
		return employee;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByDepartment(Department department) {
		List<Employee> employees = Collections.EMPTY_LIST;
		
		if (department != null && department.getId() != null) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("department.id", department.getId()));
			
			LOG.debug("finding Employee instance by Department: " + department.getName());
			employees = this.hibernateTemplate.findByCriteria(criteria);
		}
		
		return employees;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByJobTitle(JobTitle jobTitle) {
		List<Employee> employees = Collections.EMPTY_LIST;
		
		if (jobTitle != null && jobTitle.getId() != null) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("jobTitle.id", jobTitle.getId()));
			
			LOG.debug("finding Employee instance by JobTitle: " + jobTitle.getName());
			employees = this.hibernateTemplate.findByCriteria(criteria);
		}
		
		return employees;
	}

	@Override
	public Integer save(Employee employee) {
		LOG.debug("saving Employee instance with name: " + employee.getFirstName() + " " + employee.getLastName());
		return (Integer) this.hibernateTemplate.save(employee);
	}

	@Override
	public void update(Employee employee) {
		LOG.debug("updating Employee instance with name: " + employee.getFirstName() + " " + employee.getLastName());
		this.hibernateTemplate.update(employee);
	}

}
