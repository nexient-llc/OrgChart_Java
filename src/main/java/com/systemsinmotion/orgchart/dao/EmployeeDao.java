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

@Repository("employeeDao")
public class EmployeeDao implements IEmployeeDao {

	/**
	 * @see org.slf4j.Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentDao.class);
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	/* (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDao#delete(com.systemsinmotion.orgchart.entity.Employee)
	 */
	@Override
	public void delete(Employee employee) {
		String fullName = employee.getFirstName() + " " + employee.getLastName();
		
		LOG.debug("Deleting employee named " + fullName);
		
		this.hibernateTemplate.delete(employee);
	}

	/* (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDao#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")	
	public List<Employee> findAll() {
		LOG.debug("Listing all employees: " + "from " + Employee.class.getName() + " order by id");
		return this.hibernateTemplate.find("from " + Employee.class.getName() + " order by id");
	}

	/* (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDao#findById(java.lang.Integer)
	 */
	@SuppressWarnings("unused")
	@Override
	public Employee findById(Integer id) {
		LOG.debug("Attempting to find an employee by id");
		
		if (id == null)
			return null;
		
		return this.hibernateTemplate.get(Employee.class, id);
	}

	/* (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDao#save(com.systemsinmotion.orgchart.entity.Employee)
	 */
	@Override
	public Integer save(Employee employee) {
		LOG.debug("Saving an Employee.");
		return (Integer) this.hibernateTemplate.save(employee);
	}

	@Override
	public Employee findByEmail(String email) {
		LOG.debug("Attempting to find an employee by email" + email);
		Employee employee = null;

		if(StringUtils.hasText(email)) {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(Employee.class);
			criteria.add(Restrictions.eq("email", email));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<Employee> employees = hibernateTemplate
					.findByCriteria(criteria);

			if (null != employees && !employees.isEmpty())
				employee = employees.get(0);
		}

		return employee;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findByManager(Employee manager) {
		LOG.debug("Attempting to find an employee by manager");
		
		if (null != manager) {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(Employee.class);
			criteria.add(Restrictions.eq("manager", manager));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			List<Employee> employees = null;
			try {
				employees = hibernateTemplate.findByCriteria(criteria);
			} catch (Exception e) {
				return null;
			}

			if (null != employees)
				return employees;
		}

		return null;
	}

	@Override
	public List<Employee> findByDepartment(Department department) {
		LOG.debug("Attempting to find an employee by department");

		if (null != department) {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(Employee.class);
			criteria.add(Restrictions.eq("department", department));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<Employee> employees = hibernateTemplate
					.findByCriteria(criteria);

			if (null != employees)
				return employees;
		}

		return null;
	}

	@Override
	public List<Employee> findByFullName(String name) {
		if(StringUtils.hasText(name)) {
			String[] splitName = StringUtils.split(name, " ");
			if (splitName != null && splitName.length > 1) {
				List<Employee> employees = null;
				
				DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
				criteria.add(Restrictions.eq("firstName", splitName[0]));
				criteria.add(Restrictions.eq("lastName", splitName[1]));

				return hibernateTemplate.findByCriteria(criteria);
			}
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDao#update(com.systemsinmotion.orgchart.entity.Employee)
	 */
	@Override
	public void update(Employee employee) {
		LOG.debug("Updating an Employee");
		this.hibernateTemplate.update(employee);;
	}

}
