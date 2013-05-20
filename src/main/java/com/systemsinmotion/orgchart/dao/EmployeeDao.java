package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

/**
 * A data access object (DAO) providing persistence and search support for Employee entities. Transaction control of the
 * save(), update() and delete() operations must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.systemsinmotion.orgchart.entity.Employee
 * @author Allen Polak
 */
@Repository("EmployeeDAO")
public class EmployeeDao implements IEmployeeDao {

	/**
	 * @see org.slf4j.Logger
	 * A generic loger for splitting outputs from instances of this class
	 */
	private static final Logger LOG = LoggerFactory.getLogger(JobTitleDAO.class);
	
	// TODO refactor using better supported framework 
	//	(see this post: 
	//		http://stackoverflow.com/questions/4067775/spring-hibernate-template-when-to-use-and-why)
	/**
	 * @see org.springframework.orm.hibernate3.HibernateTemplate
	 */
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmpolyeeDao#delete(com.systemsinmotion.orgchart.entity.Employee)
	 */
	@Override
	public void delete(Employee employee) {
		LOG.debug("deleting Employee instance with id:name = "
			+ employee.getId() + ":" + employee.getFirstName() + " " + employee.getLastName());
		try {
			this.hibernateTemplate.delete(employee);
		} catch (RuntimeException re) {
			LOG.error("delete failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDAO#save(com.systemsinmotion.orgchart.entity.Empolyee)
	 */
	@Override
	public Integer save(Employee employee) {
		LOG.debug("saving Employee instance with id:fullname = " + employee.getId() 
			+ ":" + employee.getFirstName() + " " + employee.getLastName());
		try {
			return (Integer) this.hibernateTemplate.save(employee);
		} catch (RuntimeException re) {
			LOG.error("save failed", re);
			throw re;
		}
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDAO#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		LOG.debug("finding all Employee instances");
		try {
			return this.hibernateTemplate.find("from " + Employee.class.getName() 
				+ " order by last_name");
		} catch (RuntimeException re) {
			LOG.error("findAll() failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDAO#findById(java.lang.Integer )
	 */
	@Override
	public Employee findById(Integer id) {
		if(id == null) { return null; }
		LOG.debug("getting Employee instance with id = " + id);
		try {
			Employee tmp = this.hibernateTemplate.get(Employee.class, id); 
			return tmp;
		} catch (RuntimeException re) {
			LOG.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDAO#findByName(java.lang.String, java.lang.String)
	 */
	@Override
	public Employee findByName(String first_name, String last_name) {
		LOG.debug("finding Employee instance by full name = " + first_name + " " + last_name);
		Employee employee = null;
		try {
			@SuppressWarnings("unchecked")
			List<Employee> employees = this.hibernateTemplate.find(
				"from " + Employee.class.getName() + " where first_name=" + first_name
				+ " last_name=" + last_name);
			if (null != employees && !employees.isEmpty()) {
				employee = employees.get(0);
			}
		} catch (RuntimeException re) {
			LOG.error("lookup failed", re);
			throw re;
		}
		return employee;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDAO#findByDepartment
	 * (com.systemsinmotion.orgchart.entity.Department)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByDepartment(Department department) {
		if(department == null) { return null; }
		LOG.debug("finding Employees by Department: " + department.getName());
		try {
			return this.hibernateTemplate.find("from " + Employee.class.getName() 
				+ " where department_id=?", department.getId());
		} catch (RuntimeException re) {
			LOG.error("lookup failed", re);
			throw re;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDAO#findByManager
	 * (com.systemsinmotion.orgchart.entity.Employee)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findByManager(Employee manager) {
		if(manager == null || manager.getId() == null) { return null; }
		LOG.debug("finding Employees by manager: " 
			+ manager.getId() + ":" + manager.getLastName() + " " + manager.getFirstName());
		try {
			return this.hibernateTemplate.find("from " + Employee.class.getName() 
				+ " where manager_id=?", manager.getId());
		} catch (RuntimeException re) {
			LOG.error("lookup failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDAO#findByEmail
	 * (java.lang.String)
	 */
	@Override
	public Employee findByEmail(String email) {
		if(email == null || email == "") { return null; }
		LOG.debug("getting Employee instance with email = " + email);
		Employee employee = null;
		try {
			@SuppressWarnings("unchecked")
			List<Employee> employees = this.hibernateTemplate.find(
				"from " + Employee.class.getName() + " where email=?", email);
			if (employees != null && !employees.isEmpty()) {
				employee = employees.get(0);
			}
		} catch (RuntimeException re) {
			LOG.error("lookup failed", re);
			throw re;
		}
		return employee;
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDAO#toString()
	 */
	@Override
	public String toString() {
		String newLine = System.getProperty("line.separator");
		return getClass().getName() + " {" + newLine + "\thibernateTemplate : " + this.hibernateTemplate.toString()
				+ newLine + "}";
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDAO#update(com.systemsinmotion.orgchart.entity.Employee)
	 */
	@Override
	public void update(Employee employee) {
		LOG.debug("Updating Employee instance with id:fullname = " 
			+ employee.getId() + ":" + employee.getFirstName() + " " + employee.getLastName());
		try {
			this.hibernateTemplate.update(employee);
		} catch (RuntimeException re) {
			LOG.error("update failed", re);
			throw re;
		}
	}

}
