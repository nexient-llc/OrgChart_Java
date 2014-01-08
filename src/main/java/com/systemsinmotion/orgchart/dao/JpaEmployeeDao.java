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
		LOG.debug("saving Employee instance with First Name: " + employee.getFirstName() + " Last Name: " + employee.getLastName());
		return (Integer) this.hibernateTemplate.save(employee);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAll() {
		LOG.debug("finding all Employee instances");
		return this.hibernateTemplate.find("from Employee emp order by emp.firstName");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findByDepartment(Department department) {
		if(department != null){
			LOG.debug("finding all Employees in department: " + department.getName());
			return this.hibernateTemplate.find("from Employee emp where emp.department.id=" + department.getId());
		}
		return null;
	}

	@Override
	public Employee findByEmail(String email) {
		LOG.debug("finding Employee instance by email: " + email);
		Employee emp = null;

		if (StringUtils.hasText(email)) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("email", email));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<Employee> employees = this.hibernateTemplate.findByCriteria(criteria);

			if (null != employees && !employees.isEmpty()) {
				emp = employees.get(0);
			}
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
		LOG.debug("updating Employee instance with name: " + employee.getFirstName() + " " + employee.getLastName());
		this.hibernateTemplate.update(employee);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findByManager(Employee manager) {
		if(manager != null){
			LOG.debug("finding all Employees with manager: " + manager.getFirstName() + " " + manager.getLastName());
			List<Employee> emps = this.hibernateTemplate.find("from Employee emp where emp.manager.id=" + manager.getId());
			if(emps.size() > 0)
				return emps;
		}
		return null;
	}
}
