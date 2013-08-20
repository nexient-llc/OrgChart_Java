package com.systemsinmotion.orgchart.dao;

import java.util.ArrayList;
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
public class EmployeeDao implements IEmployeeDao {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeDao.class);
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public void delete(Employee employee) {
		LOG.debug("deleting employee instance with email: " + employee.getEmail());
		this.hibernateTemplate.delete(employee);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		LOG.debug("finding all Employee instances");
		return this.hibernateTemplate.find("from " + Employee.class.getName() + " order by name");
	}

	@Override
	public Employee findById(Integer id) {
		LOG.debug("getting Employee instance with id: " + id);
		Employee empl = null;
		
		if (id != null){
			empl = this.hibernateTemplate.get(Employee.class, id);
		}
		return empl;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByFirstName(String first_name) {
		LOG.debug("finding Employee instances by first name: " + first_name);
		List<Employee> empl = Collections.EMPTY_LIST;
		
		if(StringUtils.hasText(first_name)){
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("first_name", first_name));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			List<Employee>employees = this.hibernateTemplate.findByCriteria(criteria);
			empl.addAll(employees);
		}
		return empl;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByLastName(String last_name) {
		LOG.debug("finding Employee instances by last name: " + last_name);
		List<Employee> empl = Collections.EMPTY_LIST;
		
		if(StringUtils.hasText(last_name)){
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("first_name", last_name));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			List<Employee>employees = this.hibernateTemplate.findByCriteria(criteria);
			empl.addAll(employees);
		}
		return empl;
	}

	@Override
	public Employee findByEmail(String email) {
		Employee empl = null;
		
		if (StringUtils.hasText(email)){
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("email", email));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			@SuppressWarnings("unchecked")
			List<Employee> employees = this.hibernateTemplate.findByCriteria(criteria);
			
			if(null != employees && !employees.isEmpty()){
				empl = employees.get(0);
			}
		}
		return empl;
	}

	@Override
	public Employee findBySkypeName(String skype_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByManagerStatus(boolean is_Manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByManager(int managerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByManager(Employee manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByJobTitle(int jobTitleId){
		//TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Employee> findByJobTitle(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByDepartment(int departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Employee employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Employee employee) {
		// TODO Auto-generated method stub

	}

}
