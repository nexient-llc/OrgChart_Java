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
	HibernateTemplate ht;
	
	//save a new employee, returns the newly generated ID
	public Integer save(Employee employee) throws RuntimeException
	{
		
		return (Integer) this.ht.save(employee);
		
	}

	//update an existing employee's information
	public void update(Employee employee) throws RuntimeException
	{

		this.ht.update(employee);
		
	}

	//delete an employee record from the system
	public void delete(Employee employee) throws RuntimeException
	{
		
		this.ht.delete(employee);
		
	}

	//find all listed employees
	@SuppressWarnings("unchecked")
	public List<Employee> findAll() throws RuntimeException
	{
		
		return this.ht.find(
				" FROM "
				+ Employee.class.getName()
				+ " ORDER BY first_name, last_name"
				);
		
	}

	//find a specific employee by their ID
	public Employee findByEmployeeID(Integer empID) throws RuntimeException 
	{
		
		return this.ht.get(Employee.class, empID);
		
	}

	//find an employee by their first name
	@SuppressWarnings("unchecked")
	public List<Employee> findByFirstName(String fName) throws RuntimeException {
		
		return this.ht.find(
				" FROM "
				+ Employee.class.getName()
				+ " WHERE first_name LIKE ? "
				, "%" + fName + "%"
				);
		
	}

	//find an employee by their last name
	@SuppressWarnings("unchecked")
	public List<Employee> findByLastName(String lName) throws RuntimeException 
	{
		
		return this.ht.find(
				" FROM "
				+ Employee.class.getName()
				+ " WHERE last_name LIKE ? "
				, "%" + lName + "%"
				);
		
	}

	//find an employee by a combination of first and last name
	@SuppressWarnings("unchecked")
	public List<Employee> findByNameCombination(String fName, String lName) throws RuntimeException 
	{
	
		return this.ht.find(
				" FROM "
				+ Employee.class.getName()
				+ " WHERE first_name LIKE ? "
				+ " AND last_name LIKE ? "
				, "%" + fName + "%" , "%" + lName + "%"
				);
		
	}

	//find employees by email
	@SuppressWarnings("unchecked")
	public List<Employee> findByEmail(String email) throws RuntimeException 
	{

		return this.ht.find(
				" FROM " 
				+ Employee.class.getName() 
				+ " WHERE email LIKE ? ", "%" + email + "%"
				);
		
	}

	//find employees by department
	@SuppressWarnings("unchecked")
	public List<Employee> findByDepartment(Department department) throws RuntimeException
	{
	
		return this.ht.find(
				" FROM "
				+ Employee.class.getName() 
				+ " WHERE department_id = "
				+ department.getDepartmentId()
				);
		
	}

	//find employees by job title
	@SuppressWarnings("unchecked")
	public List<Employee> findByJobTitle(JobTitle jobTitle) throws RuntimeException 
	{
		
		return this.ht.find(
				" FROM "
				+ Employee.class.getName()
				+ " WHERE job_title_id = "
				+ jobTitle.getJobTitleID()
				);
		
	}

	//find employees by manager
	@SuppressWarnings("unchecked")
	public List<Employee> findByManager(Employee employee) throws RuntimeException
	{
		
		return this.ht.find(
				" FROM "
				+ Employee.class.getName() 
				+ " WHERE manager_id = "
				+ employee.getEmpID()
				);
		
	}
	
}
