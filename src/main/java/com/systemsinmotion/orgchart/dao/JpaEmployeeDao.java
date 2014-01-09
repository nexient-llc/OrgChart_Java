package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

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
	
	/* (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.EmployeeDao#save(com.systemsinmotion.orgchart.entity.Employee)
	 */
	@Override
	public Integer save(Employee employee) {
		LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>saving Employee instance with first name: " + employee.getFirstName() + ", last name: " + employee.getLastName());
		LOG.info(">>>>>>>>>>>>>>>>>>>>>>>" + employee.getDepartment().getName());
		return (Integer)this.hibernateTemplate.save(employee);
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.EmployeeDAO#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		LOG.debug("finding all Employee instances");
		return this.hibernateTemplate.find("from " + Employee.class.getName() + " order by name");
	}

}
