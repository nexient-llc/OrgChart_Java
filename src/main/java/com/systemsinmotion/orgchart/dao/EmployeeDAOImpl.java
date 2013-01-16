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

	public Integer save(Employee employee) {
		
		//try saving the employee object to the database, return the new employee id
		try 
		{
			return (Integer) this.hibernateTemplate.save(employee);
		} 
		catch (RuntimeException ex) 
		{
			throw ex;
		}
	}

	public void update(Employee employee) {
		
		//try updating the selected employee record
		try
		{
			this.hibernateTemplate.update(employee);
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}

	}

	public void delete(Employee employee) {
		
		//try to delete the selected employee record
		try
		{
			this.hibernateTemplate.delete(employee);
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		
		//retrieve all the employee records from the database
		try 
		{
			return this.hibernateTemplate.find(" FROM "
					+  Employee.class.getName()
					+ " ORDER BY first_name, last_name");
		} 
		catch (RuntimeException ex) 
		{
			throw ex;
		}
	}

	public Employee findByEmployeeID(Integer ID) {
		
		//retrieve a specific employee record by employeeID
		try
		{
			return this.hibernateTemplate.get(Employee.class, ID);
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findByFirstName(String firstName) {
		
		//find employees by first name or partial first name
		try 
		{					
				return this.hibernateTemplate.find(" FROM " 
						+ Employee.class.getName()
						+ "WHERE first_name like ", "%" + firstName + "%");
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> findByLastName(String lastName) {
		
		//find employees by last or partial last name
		try 
		{
			return this.hibernateTemplate.find(" FROM " 
					+ Employee.class.getName()
					+ "WHERE last_name like ", "%" + lastName + "%");
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findByEmail(String email) {
		
		//find employees by email
		try
		{
			return this.hibernateTemplate.find(" FROM " 
					+ Employee.class.getName() 
					+ " WHERE email LIKE ?", "%" + email + "%");
		}
		catch (RuntimeException ex) 
		{
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findByDepartment(Department department) {
		
		//Retrieve all employees for a selected department
		try 
		{
			return this.hibernateTemplate.find(" FROM "
					+ Employee.class.getName() 
					+ " WHERE department_id = "
					+ department.getDepartmentId());
		} 
		catch (RuntimeException ex) 
		{
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findByJobTitle(JobTitle jobTitle) {
		
		//Retrieve all employees for a selected job title
		try 
		{
			return this.hibernateTemplate.find(" FROM "
					+ Employee.class.getName()
					+ " WHERE job_title_id = "
					+ jobTitle.getJobTitleID());
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findByManager(Employee employee) {
		
		//Retrieve all subordinate employees for a selected manager
		try {
			return this.hibernateTemplate.find(" FROM "
					+ Employee.class.getName() 
					+ " WHERE manager_id = "
					+ employee.getEmpID());
		} 
		catch (RuntimeException ex) 
		{
			throw ex;
		}
	}

}
