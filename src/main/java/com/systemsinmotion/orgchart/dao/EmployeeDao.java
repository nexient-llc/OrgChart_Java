package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

/**
 * A data access object (DAO) providing persistence and search support for Employee entities. 
 * Transaction control of the save(), update() and delete() operations must be handled externally by 
 * senders of these methods or must be manually added to each of these methods for data to be persisted to 
 * the JPA datastore. 
 * @see com.systemsinmotion.orgchart.entity.Department
 * @author Matt Arnold
 */
@Repository("employeeDao")
public class EmployeeDao implements com.systemsinmotion.orgchart.dao.IEmployeeDao {
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public void delete(Employee employee) {
		// LOG.debug("deleting Employee instance with name: " + employee.getName());
		this.hibernateTemplate.delete(employee);
	}

	/*
	 * (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.DepartmentDAO#save(com.systemsinmotion .orgchart.entity.Department)
	 */
	@Override
	public Integer save(Employee employee) {
		//LOG.debug("saving Employee instance with name: " + employee.getName());
		return (Integer) this.hibernateTemplate.save(employee);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		// LOG.debug("finding all Employee instances");
		return this.hibernateTemplate.find("from " + Employee.class.getName() + " order by name");
	}

	@Override
	public Employee findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Employee> findByDepartment(Department name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Employee> findByManager(Employee name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Employee findByEmail(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}