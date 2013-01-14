package com.systemsinmotion.orgchart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.systemsinmotion.orgchart.entity.Department;
import com.systemsinmotion.orgchart.entity.Employee;
import com.systemsinmotion.orgchart.entity.JobTitle;

@Repository("employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public Integer save(Employee employee) {
		//try saving the employee object to the database
		//if it errors, throw the exception
		try 
		{
			return (Integer) this.hibernateTemplate.save(employee);
		} 
		catch (RuntimeException e) 
		{
			throw e;
		}
	}

	@Override
	public void update(Employee employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Employee employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findByEmployeeID(Integer ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByJobTitle(JobTitle jobTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByManager(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

}
