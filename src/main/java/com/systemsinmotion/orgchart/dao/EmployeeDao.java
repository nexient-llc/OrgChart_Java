package com.systemsinmotion.orgchart.dao;

import java.util.Collections;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;

@Repository
public class EmployeeDao implements com.systemsinmotion.orgchart.dao.IEmployeeDao{
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public Integer save(Employee employee) {
		return (Integer) this.hibernateTemplate.save(employee);
	}

	@Override
	public void update(Employee employee) {
		this.hibernateTemplate.update(employee);
		
	}

	@Override
	public void delete(Employee employee) {
		this.hibernateTemplate.delete(employee);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		List<Employee> employees = Collections.EMPTY_LIST;
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		employees = this.hibernateTemplate.findByCriteria(criteria);
		return employees;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> findByDepartment(Department department){
		List<Employee> employees = Collections.EMPTY_LIST;

		if (department != null && department.getId() != null) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("department.id", department.getId()));
			employees = this.hibernateTemplate.findByCriteria(criteria);
			return employees;
		}
		
		return null;
		
	}

	@SuppressWarnings("unchecked")
	public Employee findByEmail(String email) {
		
		Employee employee = null;
		
		if(StringUtils.hasText(email)){
			
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("email", email));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<Employee> employees = this.hibernateTemplate.findByCriteria(criteria);
			
			if (employees != null && !employees.isEmpty()){
				employee = employees.get(0);
				}
			}
		return employee;
	}

	@Override
	public Employee findById(Integer id) {
		Employee employee = null;
		if(id != null){
			employee = this.hibernateTemplate.get(Employee.class, id);
		}
		return employee;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findByManager(Employee manager) {
		List<Employee> employees = Collections.EMPTY_LIST;
		if(manager != null){
			DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
			criteria.add(Restrictions.eq("manager.id", manager.getId()));
			employees = this.hibernateTemplate.findByCriteria(criteria);
			if(!employees.isEmpty()){
				return employees;
			}
		}
		return null;
	}
}

