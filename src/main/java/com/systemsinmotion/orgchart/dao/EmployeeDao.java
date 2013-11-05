package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Employee;

@Repository("employeeDao")
public class EmployeeDao implements IEmployeeDao {

	/**
	 * @see org.slf4j.Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentDao.class);
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
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
	@Override
	public Employee findById(Integer id) {
		LOG.debug("Attempting to find an employee by id" + id.toString());
		
		Employee employee = null;
		
		if( null != id ) {
			employee = this.hibernateTemplate.get(Employee.class, id);
		}
		
		return employee;
	}

	/* (non-Javadoc)
	 * @see com.systemsinmotion.orgchart.dao.IEmployeeDao#save(com.systemsinmotion.orgchart.entity.Employee)
	 */
	@Override
	public Integer save(Employee employee) {
		LOG.debug("Saving an Employee.");
		return (Integer) this.hibernateTemplate.save(employee);
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
