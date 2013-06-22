package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.systemsinmotion.orgchart.entity.Employee;

@Repository
public class EmployeeDao implements com.systemsinmotion.orgchart.dao.IEmployeeDao{
	
	public static final Logger log = LoggerFactory.getLogger(EmployeeDao.class);
	
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
	@Override
	public List<Employee> findAll() {
		List<Employee> employee;
		employee = this.hibernateTemplate.find("FROM" + Employee.class.getName());
		return employee;
	}
	
	
}
