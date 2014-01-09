package com.systemsinmotion.orgchart.dao;


import java.util.Collections;
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

@Repository("employeeDao")
public class JpaEmployeeDao implements EmployeeDao {
	
	/**
	 * @see org.slf4j.Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(JpaEmployeeDao.class);

	/**
	 * @see org.springframework.orm.hibernate3.HibernateTemplate
	 */
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public Integer save(Employee employee) {
		LOG.debug("saving Employee instance with name: " + employee.getLastName());
		return (Integer) this.hibernateTemplate.save(employee);
	}

	@Override
	public List<Employee> findAll() {
		return this.hibernateTemplate.find("from Employee emp order by lastName");
	}

	@Override
	public List<Employee> findByDepartment(Department department) {
		if( department != null)
		{
			return this.hibernateTemplate.find("from Employee emp where emp.department.id=" + department.getId());
		}
		return null;
	}

	@Override
	public Employee findByEmail(String email) {
		Employee emp = null;
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		List<Employee> employees = this.hibernateTemplate.findByCriteria(criteria);

		if (!employees.isEmpty()) {
			emp = employees.get(0);
		}
		return emp;
	}

	@Override
	public Employee findById(Integer id) {
		LOG.debug("getting Employee instance with id: " + id);
		Employee emp = null;

		if (id != null) {
			emp = this.hibernateTemplate.get(Employee.class, id);
		}
		return emp;
	}

	@Override
	public void update(Employee employee) {
		this.hibernateTemplate.update(employee);
	}

	@Override
	public List<Employee> findByManager(Employee manager) {
		List<Employee> emps = Collections.EMPTY_LIST;

		if (manager != null && manager.getId() != null) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("manager.id", manager.getId()));

			LOG.debug("finding Employees for Manager: " + manager.getLastName());
			emps = this.hibernateTemplate.findByCriteria(criteria);
			return emps;
		}
		return null;
	}

}
