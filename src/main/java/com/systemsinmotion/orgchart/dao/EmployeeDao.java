package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

/**
 * A data access object (DAO) providing persistence and search support for Employee entities. Transaction control of the
 * save(), update() and delete() operations must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.systemsinmotion.orgchart.entity.Employee
 * @author Steven Byks
 */
@Repository("employeeDao")
public class EmployeeDao implements com.systemsinmotion.orgchart.dao.IEmployeeDao {

	/**
	 * @see org.slf4j.Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentDao.class);

	/**
	 * @see org.springframework.orm.hibernate3.HibernateTemplate
	 */
	@Autowired
	HibernateTemplate hibernateTemplate;

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.EmployeeDao#delete(com.systemsinmotion.orgchart.entity.Employee)
	 */
	@Override
	public void delete(Employee employee) {
		LOG.debug("deleting Employee instance with name: " + employee.PrintFullName() + " " + employee.getLastName());
		try {
			this.hibernateTemplate.delete(employee);
		} catch (RuntimeException re) {
			LOG.error("delete failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.EmployeeDao#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		LOG.debug("finding all Employee instances");
		try {
			return this.hibernateTemplate.find("from " + Employee.class.getName() + " order by last_name");
		} catch (RuntimeException re) {
			LOG.error("findAll() failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.systemsinmotion.orgchart.dao.EmployeeDao#findByDepartment(com.systemsinmotion.orgchart.entity.Department)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByDepartment(Department department) {
		// List<Employee> emps = Collections.EMPTY_LIST;
		List<Employee> emps = null;

		if (department != null && department.getId() != null) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("department.id", department.getId()));

			LOG.debug("finding employees in Department: " + department.getName());
			emps = this.hibernateTemplate.findByCriteria(criteria);
		}
		return emps;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.EmployeeDao#findByEmail(java.lang.String)
	 */
	@Override
	public Employee findByEmail(String email) {
		LOG.debug("finding Employee instance by email: " + email);
		Employee emp = null;

		if (StringUtils.hasText(email)) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("email", email));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<Employee> emps = this.hibernateTemplate.findByCriteria(criteria);

			if (emps != null && !emps.isEmpty()) {
				emp = emps.get(0);
			}
		}
		return emp;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.EmployeeDao#findById(java.lang.Integer)
	 */
	@Override
	public Employee findById(Integer id) {
		if (id == null)
			return null;

		LOG.debug("getting Employee instance with id: " + id);
		try {
			return this.hibernateTemplate.get(Employee.class, id);
		} catch (RuntimeException re) {
			LOG.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.EmployeeDao#save(com.systemsinmotion.orgchart.entity.Employee)
	 */
	@Override
	public Integer save(Employee employee) {
		LOG.debug("saving Employee instance with name: " + employee.PrintFullName());
		try {
			return (Integer) this.hibernateTemplate.save(employee);
		} catch (RuntimeException re) {
			LOG.error("save failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDao#update(com.systemsinmotion.orgchart.entity.Department)
	 */
	@Override
	public void update(Employee employee) {
		LOG.debug("updating Employee instance with name: " + employee.PrintFullName());
		this.hibernateTemplate.update(employee);
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#toString()
	 */
	@Override
	public String toString() {
		String newLine = System.getProperty("line.separator");
		return getClass().getName() + " {" + newLine + "\thibernateTemplate : " + this.hibernateTemplate.toString()
				+ newLine + "}";
	}

}