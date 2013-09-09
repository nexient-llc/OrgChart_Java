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
import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository("employeeDao")
public class EmployeeDao implements com.systemsinmotion.orgchart.dao.IEmployeeDao {

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
		return this.hibernateTemplate.find("from " + Employee.class.getName() + " order by FIRST_NAME");
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
		LOG.debug("finding Employee instance by skype name: " + skype_name);
		Employee empl = null;

		if (StringUtils.hasText(skype_name)){
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("skype_name", skype_name));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			@SuppressWarnings("unchecked")
			List<Employee> employees = this.hibernateTemplate.findByCriteria(criteria);

			if (null != employees && !employees.isEmpty()){
				empl = employees.get(0);
			}
		}
		return empl;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByManagerStatus(boolean is_Manager) {
		List<Employee> empls = Collections.EMPTY_LIST;

		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		criteria.add(Restrictions.eq("is_Manager", is_Manager));

		LOG.debug("finding all Employees with manager status: " + is_Manager);
		empls = this.hibernateTemplate.findByCriteria(criteria);

		return empls;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByManager(Employee manager) {
		List<Employee> empls = Collections.EMPTY_LIST;

		if(manager != null && manager.getId() != null){
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("manager_id", manager.getId()));

			LOG.debug("finding all Employees with the Manager: " + manager.getFirstName() + " " + manager.getLastName());
			empls = this.hibernateTemplate.findByCriteria(criteria);
		}
		return empls;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByJobTitle(int jobTitleId){
		List<Employee> empls = Collections.EMPTY_LIST;

		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		criteria.add(Restrictions.eq("jobTitle.id", jobTitleId));

		LOG.debug("finding all Employees with the JobTitle ID of: " + jobTitleId);
		empls = this.hibernateTemplate.findByCriteria(criteria);
			
		return empls;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByJobTitle(JobTitle jobTitle) {
		List<Employee> empls = Collections.EMPTY_LIST;

		if(jobTitle != null && jobTitle.getId() != null){
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("jobTitle.id", jobTitle.getId()));

			LOG.debug("finding all Employees with the Job Title: " + jobTitle.getName());
			empls = this.hibernateTemplate.findByCriteria(criteria);
		}
		return empls;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByDepartment(int departmentId) {
		List<Employee> empls = Collections.EMPTY_LIST;

		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		criteria.add(Restrictions.eq("department.id", departmentId));

		LOG.debug("finding all Employees with the Department ID of: " + departmentId);
		empls = this.hibernateTemplate.findByCriteria(criteria);
			
		return empls;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> findByDepartment(Department department) {
		List<Employee> empls = Collections.EMPTY_LIST;

		if(department != null && department.getId() != null){
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("department.id", department.getId()));

			LOG.debug("finding all Employees with the Department Title: " + department.getName());
			empls = this.hibernateTemplate.findByCriteria(criteria);
		}
		return empls;
	}

	@Override
	public int save(Employee employee) {
		LOG.debug("Saving Employee instance with email: " + employee.getEmail());
		return (Integer) this.hibernateTemplate.save(employee);
	}

	@Override
	public void update(Employee employee) {
		LOG.debug("updating Department instance with information: \n" +
				  "Name: " + employee.getFirstName() + " " + employee.getLastName() +
				  "\nEmail: " + employee.getEmail() + "\nSkype: " + employee.getSkypeName());
		this.hibernateTemplate.update(employee);
	}

}
